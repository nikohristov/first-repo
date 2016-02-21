
public class PeddlerTrader extends Trader{

	private SmallProvider ownProvider;
	private double lastOrder;
	
	public PeddlerTrader(String name, String address, double capital) {
		super(name, address, capital);
		this.ownProvider = null;
		this.lastOrder = 0;
	}

	@Override
	void anOrder(int amount,Provider provider) {
		if(amount > (this.getCapital()*0.5)){
			throw new IllegalArgumentException("Not enoughf money to order "+this.getName());
		}else{
			this.getDiscount(provider.makeDiscount(amount));
			this.lastOrder = amount;
			this.payForStock(amount);
		}
	}

	@Override
	void payTax(TradeCenter c) {
		System.out.println(this.getName()+" don't have trade center.");
	}

	@Override
	void getMoneyOfSells(TradeCenter c) {
		this.getDiscount(this.lastOrder*1.3);
	}
	
	protected void addOwnProvider(SmallProvider c){
		if(this.ownProvider == null)
			this.ownProvider = c;
	}

}
