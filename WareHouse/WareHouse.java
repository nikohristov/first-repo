package WareHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class WareHouse extends Thread{
	
	private static final String[] types = {"MEATS","FRUITS","VEGETABLES"};
	protected static final String[][] productTypes = {{"Pork","Beef","Chicken"},
													{"Banana","Orange","Apple"},
														{"Potato","Eggplant","Cucumber"}};
	static final int MIN_QUANTITY = 5;
	
	private HashMap<String,HashMap<String,Integer>> stock;
	
	public WareHouse() {
		this.stock = new HashMap<String,HashMap<String,Integer>>();
		
		for(int i=0; i<this.types.length; i++){
			this.stock.put(this.types[i], new HashMap<>());
			for(int j=0; j<this.productTypes[0].length; j++){
				this.stock.get(this.types[i]).put(this.productTypes[i][j], 15);
			}
		}
	}
	
	protected void printStockInWareHouse(){
		for(Map.Entry<String, HashMap<String,Integer>> map : this.stock.entrySet()){
			System.out.println(map.getKey()+ " :");
			for(Map.Entry<String, Integer> prodName : map.getValue().entrySet()){
				System.out.println(prodName.getKey() +" : "+prodName.getValue());
			}
		}
	}
	
	protected ArrayList<String> isDepleted(){
		ArrayList<String> depleted = new ArrayList<>();
		for(Map.Entry<String, HashMap<String,Integer>> map : this.stock.entrySet()){
			for(Map.Entry<String, Integer> prodMap : map.getValue().entrySet()){
				if(prodMap.getValue() <= this.MIN_QUANTITY){
					depleted.add(prodMap.getKey());
				}
			}
		}
		return depleted;
	}
	
	synchronized protected void DeliverStock() throws InterruptedException{
		ArrayList<String> prod = this.isDepleted();
		if(prod.size() == 0){
			wait();
		}else{
			for(String s : prod){
				for(Map.Entry<String, HashMap<String,Integer>> map : this.stock.entrySet() ){
					String type = map.getKey();
					for(Map.Entry<String,Integer> prodMap : map.getValue().entrySet()){
						if(s.equals(prodMap.getKey())){
							this.stock.get(type).put(s, prodMap.getValue()+25);
							System.out.println(Thread.currentThread().getName()+" added "+s);
							this.printStockInWareHouse();
						}
					}
				}
			}
			notifyAll();
		}
	}
	
	protected boolean isProductDepleted(String name){
		for(Map.Entry<String, HashMap<String,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<String,Integer> prodMap : map.getValue().entrySet()){
				if(name.equals(prodMap.getKey())){
					if(prodMap.getValue() <= this.MIN_QUANTITY)
						return true;
				}
			}
		}
		return false;
	}
	
	synchronized protected int GiveStock(String name) throws InterruptedException{
		if(this.isProductDepleted(name)){
			wait();
		}else{
			this.DecreaseGivenStock(name,5);
			notifyAll();
			return 5;
		}
		return 0;
	}
	
	
	protected void DecreaseGivenStock(String name,int quantity){
		for(Map.Entry<String, HashMap<String,Integer>> map : this.stock.entrySet()){
			String type = map.getKey();
			for(Map.Entry<String,Integer> prodMap : map.getValue().entrySet()){
				if(name.equals(prodMap.getKey())){
					this.stock.get(type).put(name, prodMap.getValue()-quantity);
				}
			}
		}
	}

	public HashMap<String, HashMap<String, Integer>> getStock() {
		return stock;
	}
	
	
}
