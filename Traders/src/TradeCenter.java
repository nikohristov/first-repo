import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;

public abstract class TradeCenter implements Comparable<TradeCenter>{

	private static final String[] PRODUCTS = {"Hranitelni stoki","Drehi","Bitovi","Str. Materiali",
											"Kuhnenski potrebnosti","Tehnika","Igrachki"};
	
	private String addres;
	private String workingTime;
	private int tax;
	private int area;
	private HashMap<String,Integer> products;
	private double sells;
	
	Random r = new Random();
	
	public TradeCenter(String address,String workingTime, int tax, int area) {
		this.workingTime = workingTime;
		this.tax = tax;
		this.addres = address;
		this.area = area;
		this.products = new HashMap<String,Integer>();
		this.sells = 0;
	}

	public String getAddres() {
		return addres;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public int getTax() {
		return tax;
	}

	public int getArea() {
		return area;
	}

	public HashMap<String, Integer> getProducts() {
		return products;
	}
	
	protected void generateStock(int amount){
		while(amount > 0){
			int value = r.nextInt(50) + 10;
			this.products.put(this.PRODUCTS[r.nextInt(this.PRODUCTS.length)], value);
			amount -= value;
		}
		ArrayList<Map.Entry<String,Integer>> sortedProd = new ArrayList<Map.Entry<String,Integer>>(this.products.entrySet());
		Collections.sort(sortedProd,new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});
		System.out.println("Generate stock for "+this.getAddres());
		for(int i =0; i<sortedProd.size(); i++){
			System.out.println(sortedProd.get(i).getKey() + " " + sortedProd.get(i).getValue()+"lv.");
		}
		System.out.println();
	}
	
	protected double sellStock(){
		if(this.products.isEmpty()){
			throw new IllegalArgumentException("The stock of this trade center is empty !");
		}
		double amount = 0;
		for(Map.Entry<String, Integer> map : this.products.entrySet()){
			int price = map.getValue();
			amount+= price;
		}
		this.products.clear();
		return amount;
	}
	
	@Override
	public int compareTo(TradeCenter o) {
		return this.addres.compareTo(o.addres);
	}
	
}
