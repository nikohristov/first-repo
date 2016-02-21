import java.awt.image.Raster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class DemoTrade {

	private static final String[] NAMES = {"Niki","Stefan","Hristo","Ivan","Kiro","Jana","Jore","Rubi","Simeon"};
	private static final String[] FAMILY = {"Hristov","Georgiev","Daskalov","Kaichkiqn","Zorov"};
	private static final String[] ADDRESS = {
			"Levski V","Drujba 2","Liulin 7","liulin 2","Liulin 3","Mladost","Obelq","Center"
	};
	private static final String[] LOCATION = {
		"17v","Jaloio 9","Rezbarska 7","Snopchi 6","zorko 9","bl 200","bl 300"
	};
	
	public static void main(String[] args) throws IllegalArgumentException{
		Random r = new Random();
		HashSet<Provider> providers = new HashSet<Provider>();
		for(int i=0; i<5; i++){
			String name = DemoTrade.NAMES[r.nextInt(DemoTrade.NAMES.length)] + " " + DemoTrade.FAMILY[r.nextInt(DemoTrade.FAMILY.length)];
			String address = DemoTrade.ADDRESS[r.nextInt(DemoTrade.ADDRESS.length)];
			providers.add(new SmallProvider(name, address, "08:00-22:00"));
		}
		for(int i=0; i<5; i++){
			String name = DemoTrade.NAMES[r.nextInt(DemoTrade.NAMES.length)] + " " + DemoTrade.FAMILY[r.nextInt(DemoTrade.FAMILY.length)];
			String address = DemoTrade.ADDRESS[r.nextInt(DemoTrade.ADDRESS.length)];
			providers.add(new LargeProvider(name, address, "08:00-22:00"));
		}
		
		ArrayList<TradeCenter> centers = new ArrayList<TradeCenter>();
		for(int i=0; i<7; i++){
			String add = DemoTrade.ADDRESS[r.nextInt(DemoTrade.ADDRESS.length)]+" "+DemoTrade.LOCATION[r.nextInt(DemoTrade.LOCATION.length)];
			centers.add(new Stall(add, "08:00-22:00", r.nextInt(8)+2));
		}
		for(int i=0; i<7; i++){
			String add = DemoTrade.ADDRESS[r.nextInt(DemoTrade.ADDRESS.length)]+" "+DemoTrade.LOCATION[r.nextInt(DemoTrade.LOCATION.length)];
			centers.add(new StreetBox(add, "08:00-22:00", 5));
		}
		for(int i=0; i<7; i++){
			String add = DemoTrade.ADDRESS[r.nextInt(DemoTrade.ADDRESS.length)]+" "+DemoTrade.LOCATION[r.nextInt(DemoTrade.LOCATION.length)];
			centers.add(new ShopInMall(add, "08:00-22:00", r.nextInt(90)+10));
		}
		
		PeddlerTrader one = new PeddlerTrader("Nikoay Hristov", "Levski V", 1000);
		ETTrader two = new ETTrader("Ivan Daskalov", "Levski G", 1500);
		TradesChain tree = new TradesChain("Vsichko za lev", "Levski", 3000);
		
		Collections.shuffle(centers);
		for(int i=0; i<centers.size(); i++){
			int n  = r.nextInt(2);
			switch(n){
			case 0:
				two.addTradeCenter(centers.get(i));
				break;
			case 1:
				tree.addTradeCenter(centers.get(i));
				break;
			}
		}
		
		ArrayList<Trader> traders = new ArrayList<Trader>();
		traders.add(one);
		traders.add(two);
		traders.add(tree);
		startTrade(traders);
		System.out.println("End of trades: ");
		for(Trader tra : traders){
			System.out.println(tra.getCapital());
		}
		
		
		
	}
	
	public static void startTrade(ArrayList<Trader> traders){
		Random r = new Random();
		for(Trader trad  : traders){
			if(trad instanceof ETTrader){
				for(Iterator it = ((ETTrader) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					c.generateStock(r.nextInt(100)+100);
				}
			}else if(trad instanceof TradesChain){
				for(Iterator it = ((TradesChain) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					c.generateStock(r.nextInt(100)+100);
				}
			}
		}
		for(Trader trad  : traders){
			if(trad instanceof ETTrader){
				for(Iterator it = ((ETTrader) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					System.out.println(trad.getName()+" get "+c.sellStock()+" by sales.");
				}
			}else if(trad instanceof TradesChain){
				for(Iterator it = ((TradesChain) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					System.out.println(trad.getName()+" get "+c.sellStock()+" by sales.");
				}
			}
		}
		for(Trader trad  : traders){
			if(trad instanceof ETTrader){
				for(Iterator it = ((ETTrader) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					trad.payTax(c);
				}
			}else if(trad instanceof TradesChain){
				for(Iterator it = ((TradesChain) trad).getCenters().iterator(); it.hasNext();){
					TradeCenter c = (TradeCenter) it.next();
					trad.payTax(c);
				}
			}
		}
		
	}

}
