import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class ETTrader extends Trader{

	private HashSet<SmallProvider> providers;
	private HashSet<TradeCenter> tradeCenters;
	
	Random r = new Random();
	
	public ETTrader(String name, String address, double capital) {
		super(name, address, capital);
		this.providers = new HashSet<SmallProvider>(5);
		this.tradeCenters = new HashSet<TradeCenter>(1);
	}
	
	protected void addTradeCenter(TradeCenter trade){
		try{
			if(this.tradeCenters.size() >= 1 || (trade instanceof ShopInMall)){
				throw new IllegalArgumentException("Invalid trade center for ETTrader "+this.getName());
			}else{
				this.tradeCenters.add(trade);
			}
		}catch(IllegalArgumentException e){
			e.getMessage();
		}
	}
	
	protected void addSmallProvider(SmallProvider small){
		try{
			if(this.providers.size() >= 5){
				throw new IllegalArgumentException("No free places for more providers "+ this.getName());
			}else{
				this.providers.add(small);
			}
		}catch(IllegalArgumentException e){
			e.getMessage();
		}
	}

	protected HashSet<TradeCenter> getCenters(){
		return this.tradeCenters;
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
	
	
	
}
