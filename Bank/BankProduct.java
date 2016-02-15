package Bank;

public abstract class BankProduct {
	
	private static String ID = "003/BG22N";
	private static int count = 0;
	private String name;
	private double yearPercent;
	private int period;
	private double cash;
	
	public BankProduct(byte yearPercent, int period,double cash) {
		this.name = this.ID + this.count++;
		this.yearPercent = (double)yearPercent/100;
		this.period = period;
		this.cash = cash;
	}
	
	public String getName() {
		return name;
	}

	public double getYearPercent() {
		return yearPercent;
	}

	public int getPeriod() {
		return period;
	}

	public double getCash() {
		return cash;
	}

	public abstract void printInfoProduct();
	
}
