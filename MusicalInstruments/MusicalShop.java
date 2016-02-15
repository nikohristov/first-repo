package MusicalInstruments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

public class MusicalShop extends Thread{
	private static final String[] defaultTypes = {"струнни","ударни","духови","клавишни","електронни"};
	private static ArrayList<String> types;
	private static final String[][] defaultInstruments = {{"цигулка", "виола", "контрабас", "арфа", "китара", "гъдулка"},
			{"барабани", "тарамбука", "тъпан", "дайре"},
			{"тромпет", "тромбон", "туба", "флейта", "кларинет"},	
			{"орган", "пиано", "акордеон"},
			{"синтезатор", "бас-китара", "електрическа цигулка"}};
	private static final int defaultCash = 1000;
	private int cash;
	private TreeMap<String,TreeMap<MusicalInstrument,Integer>> stock;
	private TreeMap<MusicalInstrument,Integer> saledStock;
	private SuplierOfInstruments own;
	
	public MusicalShop() {
		this.cash = this.defaultCash;
		this.types = new ArrayList<String>();
		for(String s : this.defaultTypes){
			this.types.add(s);
		}
		
		this.stock = new TreeMap<String,TreeMap<MusicalInstrument,Integer>>();
		this.saledStock = new TreeMap<MusicalInstrument,Integer>();
		Random r = new Random();
		
		for(int j=0; j<this.defaultTypes.length; j++){
			this.stock.put(this.defaultTypes[j], new TreeMap<MusicalInstrument,Integer>());
			for(int i=0; i<this.defaultInstruments[j].length; i++){
				int quantityOfStock = r.nextInt(10) + 5;
				int priceOfStock = r.nextInt(10) + 10;
				this.stock.get(this.defaultTypes[j]).put(new MusicalInstrument(this.defaultInstruments[j][i], priceOfStock), quantityOfStock);
			}
		}
		this.own = new SuplierOfInstruments(this);
	}
	
	protected int getCash(){
		return this.cash;
	}
	
	private void increaseMoney(int quantity){
		this.cash += quantity;
	}
	
	private boolean checkProduct(String name){
		boolean flag = false;
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
				if(mapOfProd.getKey().getName().equals(name)){
					flag = true;
					return flag;
				}
			}
		}
		return flag;
	}
	
	protected void sellInstrument(String name,Integer quantity) throws InterruptedException{
		
		try{
			if(!(this.checkProduct(name))){
				throw new IllegalArgumentException("Invalid name of stock to sell !: " +name);
			}
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		}
		try{
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
					if(mapOfProd.getKey().getName().equals(name)){
							if(mapOfProd.getValue() >= quantity){
							this.stock.get(type).put(mapOfProd.getKey(),(mapOfProd.getValue() - quantity));
							this.increaseMoney(mapOfProd.getKey().getPrice()*quantity);
							System.out.println("The shop succesfully sold "+name+" "+quantity+".");
							this.writeToSaledStock(mapOfProd.getKey(), quantity);
							return;
						}
					}
			}
		}
		throw new IllegalArgumentException("The quantity of "+name+" is not enough!");
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			this.own.giveRequest(name, quantity);
			return;
		}
	}
	
	protected void getNewInstrument(String name,int quantity){
		
		try{
			if(!(this.checkProduct(name))){
				throw new IllegalArgumentException("Invalid name of stock to add !: " +name);
			}
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		}
		
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
				if(mapOfProd.getKey().getName().equals(name)){
					this.stock.get(type).put(mapOfProd.getKey(), mapOfProd.getValue()+quantity);
					//System.out.println("You succesfully added "+quantity+" of "+name);
				}
			}
		}
	}
	
	protected void printCatalogByType(){
		System.out.println("\t Cataloge by type:");
		for(Map.Entry<String, TreeMap<MusicalInstrument, Integer>> map : this.stock.entrySet()){
			System.out.println("\t "+map.getKey());
			for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
				mapOfProd.getKey().printInfoInstrument();
				System.out.println(" available "+mapOfProd.getValue());
			}
		}
	}
	
	protected void printCatalogSortByName(){
		System.out.println("\t Cataloge sorted by name:");
		TreeMap<MusicalInstrument,Integer> byName = new TreeMap<MusicalInstrument,Integer>(new Comparator<MusicalInstrument>() {
			@Override
			public int compare(MusicalInstrument o1, MusicalInstrument o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		
		for(TreeMap<MusicalInstrument,Integer> instrument : this.stock.values()){
			byName.putAll(instrument);
		}
		for(Map.Entry<MusicalInstrument,Integer> e : byName.entrySet()){
			e.getKey().printInfoInstrument();
			System.out.println(" available "+e.getValue());
		}
		
	}
	
	protected void printCatalogSortByPrice(String upOrDown){
		String value = upOrDown.toLowerCase();
		try{
			if(!((value.equals("up")) || (value.equals("down")))){
				throw new IllegalArgumentException("Invalid input for up or down !");
			}
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
			return;
		}
		
		TreeMap<MusicalInstrument,Integer> byPrice;
		
		switch(value){
			case "up":
					System.out.println("\t Cataloge sorted by growing price: ");
					byPrice = new TreeMap<MusicalInstrument,Integer>(new Comparator<MusicalInstrument>() {

						@Override
						public int compare(MusicalInstrument o1, MusicalInstrument o2) {
							if(o1.getPrice() >= o2.getPrice()){
								return 1;
							}
							else return -1;
							}
					});
					for(TreeMap<MusicalInstrument,Integer> instrument : this.stock.values()){
						byPrice.putAll(instrument);
					}
					for(Map.Entry<MusicalInstrument,Integer> e : byPrice.entrySet()){
						e.getKey().printInfoInstrument();
						System.out.println(" available "+e.getValue());
					}
					break;
			case "down":
					System.out.println("\t Cataloge sorted by decrease price: ");
					byPrice = new TreeMap<MusicalInstrument,Integer>(new Comparator<MusicalInstrument>() {

					@Override
					public int compare(MusicalInstrument o1, MusicalInstrument o2) {
						if(o1.getPrice() >= o2.getPrice()){
							return -1;
						}else 
							return 1;
					}
				});
				
				for(TreeMap<MusicalInstrument,Integer> instrument : this.stock.values()){
					byPrice.putAll(instrument);
				}
				for(Map.Entry<MusicalInstrument,Integer> e : byPrice.entrySet()){
					e.getKey().printInfoInstrument();
					System.out.println(" available "+e.getValue());
				}
				break;
		}
		
	}
	
	protected void printCatalogIsAvailable(){
		TreeMap<MusicalInstrument,Integer> isAvailable = new TreeMap<MusicalInstrument,Integer>();
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
				if(mapOfProd.getValue() > 0){
					isAvailable.put(mapOfProd.getKey(),mapOfProd.getValue());
				}
			}
		}
		
		for(Map.Entry<MusicalInstrument,Integer> e : isAvailable.entrySet()){
			e.getKey().printInfoInstrument();
			System.out.println(" available "+e.getValue());
		}
		
	}

	private void writeToSaledStock(MusicalInstrument music,int sales){
		for(Map.Entry<MusicalInstrument, Integer> map : this.saledStock.entrySet()){
			if(map.getKey().getName().equals(music.getName())){
				this.saledStock.put(music, map.getValue() + sales);
				return;
			}
		}
		this.saledStock.put(music, sales);
	}
	
	protected void printCatalogOfSales(){
		System.out.println("\t Catalog of sales sorted by number of sales:");
		ArrayList<Map.Entry<MusicalInstrument,Integer>> sortBySales 
			= this.getSortedSales();
		
		for(Map.Entry<MusicalInstrument, Integer> map : sortBySales){
			System.out.println(map.getKey().getName()+" "+map.getValue()+" sales.");
		}
	}	
	
	private ArrayList getSortedSales(){
		ArrayList<Map.Entry<MusicalInstrument,Integer>> sortBySales 
		= new ArrayList<Map.Entry<MusicalInstrument,Integer>>(this.saledStock.entrySet());
		Collections.sort(sortBySales,new Comparator<Map.Entry<MusicalInstrument, Integer>>() {

		@Override
		public int compare(Entry<MusicalInstrument, Integer> o1, Entry<MusicalInstrument, Integer> o2) {
				if(o1.getValue() >= o2.getValue()){
					return -1;
				}else 
					return 1;
				}
		});
		return sortBySales;
	}
	
	protected void genereteSumOfSales(){
		System.out.println(this.cash-this.defaultCash);
	}
	
	protected void mostSoldProduct(){
		ArrayList<Map.Entry<MusicalInstrument,Integer>> sortBySales 
		= this.getSortedSales();
		System.out.println("The most sold product: ");
		sortBySales.get(0).getKey().printInfoInstrument();
		System.out.println(" with sales "+sortBySales.get(0).getValue());
	}
	
	protected void mostNoSoldProduct(){
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			for(Map.Entry<MusicalInstrument, Integer> prodOfMap : map.getValue().entrySet()){
				if(!(this.saledStock.containsKey(prodOfMap.getKey()))){
					this.saledStock.put(prodOfMap.getKey(),0);
					break;
				}
			}
		}
		ArrayList<Map.Entry<MusicalInstrument,Integer>> sortBySales 
		= this.getSortedSales();
		System.out.println("The most no sold product: ");
		sortBySales.get((sortBySales.size()-1)).getKey().printInfoInstrument();
		System.out.println(" with sales "+sortBySales.get(sortBySales.size()-1).getValue());
	}
	
	private String getTypeOFInstrumen(MusicalInstrument m){
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<MusicalInstrument, Integer> prodOfMap : map.getValue().entrySet()){
				if(prodOfMap.getKey().getName().equals(m.getName())){
					return type;
				}
			}
		}
		return null;
	}
	
	protected void infoForMostSoldProductsByType(){
		TreeMap<String,Integer> soldProduct = new TreeMap<>();
		for(Map.Entry<MusicalInstrument, Integer> map : this.saledStock.entrySet()){
			String type = this.getTypeOFInstrumen(map.getKey());
			Integer p = soldProduct.get(type);
			if(p == null){
				p = 0;
			}
			soldProduct.put(type, p + map.getValue());
		}
		ArrayList<Map.Entry<String, Integer>> sort = new ArrayList<Map.Entry<String,Integer>>(soldProduct.entrySet());
		Collections.sort(sort,new  Comparator<Map.Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o1.getValue() >= o2.getValue()){
					return -1;
				}
				else return 1;
			}
		
		});
		System.out.println("\t The most sold product is of type "+sort.get(0).getKey()+" with "+sort.get(0).getValue()+" sales");
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			if(map.getKey().equals(sort.get(0).getKey())){
				for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
					mapOfProd.getKey().printInfoInstrument();
					System.out.println(" available "+mapOfProd.getValue());
				}
			}
		}
	}
	
	private Integer genereteIncomeOfOneProduct(MusicalInstrument m, Integer sales){
		return m.getPrice()*sales;
	}
	
	protected void infoForMostSoldProductByIncome(){
		TreeMap<String,Integer> soldProduct = new TreeMap<>();
		for(Map.Entry<MusicalInstrument, Integer> map : this.saledStock.entrySet()){
			String type = this.getTypeOFInstrumen(map.getKey());
			Integer n = this.genereteIncomeOfOneProduct(map.getKey(), map.getValue());
			if(n == null){
				n = 0;
			}
			Integer p = soldProduct.get(type);
			if(p == null){
				p=0;
			}
			soldProduct.put(type, n + p);
		}
		ArrayList<Map.Entry<String, Integer>> sort = new ArrayList<Map.Entry<String,Integer>>(soldProduct.entrySet());
		Collections.sort(sort,new  Comparator<Map.Entry<String,Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if(o1.getValue() >= o2.getValue()){
					return -1;
				}
				else return 1;
			}
		
		});
		System.out.println("\t The most sold product is of type "+sort.get(0).getKey()+" with "+sort.get(0).getValue()+" income by sales");
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			if(map.getKey().equals(sort.get(0).getKey())){
				for(Map.Entry<MusicalInstrument, Integer> mapOfProd : map.getValue().entrySet()){
					mapOfProd.getKey().printInfoInstrument();
					System.out.println(" available "+mapOfProd.getValue());
				}
			}
		}
	}
	
	private ArrayList<String> DepletedStock(){
		ArrayList<String> depletedStock = new ArrayList<String>();
		for(Map.Entry<String, TreeMap<MusicalInstrument,Integer>> map : this.stock.entrySet()){
			for(Map.Entry<MusicalInstrument, Integer> prodOfMap : map.getValue().entrySet()){
				if(prodOfMap.getValue().equals(0)){
					depletedStock.add(prodOfMap.getKey().getName());
				}
			}
		}
		return depletedStock;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.currentThread().sleep(15000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			ArrayList<String> depleted = this.DepletedStock();
			for(String s : depleted){
				try {
					this.own.giveRequest(s, 10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
