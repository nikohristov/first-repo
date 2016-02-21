
public abstract class Trader {
	
	private String name;
	private String address;
	private double capital;
	
	public Trader(String name,String address,double capital) {
		this.name = name;
		this.address = address;
		this.capital = capital;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public double getCapital() {
		return capital;
	}
	
	protected void getDiscount(double amount){
		this.capital += amount;
	}
	
	protected void payForStock(int amount){
		this.capital -= amount;
	}
	
	abstract void anOrder(int amount,Provider provider);
	
	abstract void payTax(TradeCenter c);
	
	abstract void getMoneyOfSells(TradeCenter c);

	
	
}
