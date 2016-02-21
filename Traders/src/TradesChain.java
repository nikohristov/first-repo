import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class TradesChain extends Trader{
	

	private HashSet<TradeCenter> tradeCenters;
	private HashSet<Provider> providers;
	
	Random r = new Random();
	
	public TradesChain(String name, String address, double capital) {
		super(name, address, capital);
		this.tradeCenters = new HashSet<TradeCenter>(10);
		this.providers = new HashSet<Provider>(15);
	}

	protected HashSet<TradeCenter> getCenters(){
		return this.tradeCenters;
	}
	
	protected void addProvider(Provider prov){
		try{
			if(this.providers.size() >= 15){
				throw new IllegalArgumentException("No free places for more providers "+ this.getName());
			}else{
				this.providers.add(prov);
			}
		}catch(IllegalArgumentException e){
			e.getMessage();
		}
	}
	
	protected void addTradeCenter(TradeCenter trade){
		try{
			if(this.tradeCenters.size() >= 10 || (trade instanceof StreetBox)){
				throw new IllegalArgumentException("Invalid trade center for ETTrader "+this.getName());
			}else{
				this.tradeCenters.add(trade);
			}
		}catch(IllegalArgumentException e){
			e.getMessage();
		}
	}

	@Override
	void anOrder(int amount, Provider provider) {
		if(amount > (this.getCapital()*0.5)){
			throw new IllegalArgumentException("Not enoughf money to order "+this.getName());
		}else{
			this.getDiscount(provider.makeDiscount(amount));
			this.payForStock(amount);
			ArrayList<TradeCenter> centers = new ArrayList<>(this.tradeCenters);
			centers.get(r.nextInt(this.tradeCenters.size())).generateStock(amount);
		}
	}

	@Override
	void payTax(TradeCenter c) {
		this.payForStock(c.getTax());
		System.out.println(this.getName()+" paid tax for "+c.getAddres());
	}

	@Override
	void getMoneyOfSells(TradeCenter c) {
		this.getDiscount(c.sellStock()*1.3);
	}
	
	
	
}
