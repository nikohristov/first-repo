package WareHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShopHouse extends WareHouse implements Runnable{

	protected String name;
	private WareHouse own;
	
	ShopHouse(String name, WareHouse own){
		super();
		this.name = name;
		this.own = own;
	}
	
	
	
	@Override
	public void run() {
		while(true){
			try {
				this.takeFromWare();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	synchronized void takeFromWare() throws InterruptedException{
		ArrayList<String> depletedStock = super.isDepleted();
		if(depletedStock.size() == 0){
			wait();
		}else{
			for(String s : depletedStock){
				int stock = this.own.GiveStock(s);
				this.increaseStock(s, stock);
				System.out.println(this.name+" laod "+s);
				this.printStockInWareHouse();
			}
			notifyAll();
		}
	}
	
	synchronized void giveToClient(String prod,int quantity) throws InterruptedException{
		if(super.isProductDepleted(prod)){
			wait();
		}else{
			super.DecreaseGivenStock(prod,quantity);
			System.out.println("Client take from "+this.name+" "+quantity+" of "+prod);
			notifyAll();
		}
	}
	
	private void increaseStock(String name, int stock){
		for(Map.Entry<String, HashMap<String, Integer>> map : this.getStock().entrySet()){
			String type = map.getKey();
			for(Map.Entry<String, Integer> prodMap : map.getValue().entrySet()){
				if(name.equals(prodMap.getKey())){
					this.getStock().get(type).put(name, prodMap.getValue()+stock);
				}
			}
		}
	}
	
}
