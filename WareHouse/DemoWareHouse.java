package WareHouse;

import java.util.Random;

public class DemoWareHouse {
	
	public static void main(String[] args) {
		WareHouse first = new WareHouse();
		first.printStockInWareHouse();
		
		Thread suplier = new Suplier("Toshko", first);
		
		Runnable one = new ShopHouse("Fantastiko",first);
		Runnable two = new ShopHouse("Billa",first);
		Runnable tree = new ShopHouse("T-Market",first);
		
		Client client1 = new Client(one);
		Client client2 = new Client(one);
		Client client3 = new Client(one);
		Client client4 = new Client(two);
		Client client5 = new Client(two);
		Client client6 = new Client(two);
		Client client7 = new Client(tree);
		Client client8 = new Client(tree);
		Client client9 = new Client(tree);
		
		suplier.start();
		((Thread) one).start();
		((Thread) two).start();
		((Thread) tree).start();
		client1.start();
		client2.start();
		client3.start();
		client4.start();
		client5.start();
		client6.start();
		client7.start();
		client8.start();
		client9.start();
		
		
		
	}
	
	static class Client extends Thread{
		
		private Runnable own;
		
		Client(Runnable own){
			this.own = own;
		}
		
		@Override
		public void run() {
			while(true){
				Random r = new Random();
				int on = r.nextInt(3);
				int tw = r.nextInt(3);
				int qun = r.nextInt(4) + 1;
				try {
					Thread.currentThread().sleep(1000);
						((ShopHouse) this.own).giveToClient(WareHouse.productTypes[on][tw], qun);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	static class Suplier extends Thread{
		
		private WareHouse own;
		
		public Suplier(String name,WareHouse house) {
			super(name);
			this.own = house;
		}
		
		@Override
		public void run() {
			
			while(true){
				try {
					this.own.DeliverStock();
				} catch (InterruptedException e) {
				}
			}
			
		}
		
	}
	
}
