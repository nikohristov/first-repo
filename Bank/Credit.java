package Bank;

public class Credit extends BankProduct {
	
	private double mounthlyTax;

	public Credit(byte yearPercent, int period,double cash) {
		super(yearPercent, period, -cash);
		double perc = (double)yearPercent/12;
		this.mounthlyTax = ((cash/period)*perc)/5;
	}
	
	public double getMounthlyTax() {
		return mounthlyTax;
	}

	@Override
	public void printInfoProduct() {
		System.out.println("Credit");
		System.out.println("Value of Credit: "+super.getCash());
		System.out.println("Period         : "+super.getPeriod());
		System.out.println("Interest       : "+super.getYearPercent());
		System.out.println("ID             : "+super.getName());
		System.out.println();
	}
	
	
	
}
