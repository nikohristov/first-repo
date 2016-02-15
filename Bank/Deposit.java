package Bank;

public class Deposit extends BankProduct{
	
	private double incomeTax;

	public Deposit(byte yearPercent, int period, double cash) {
		super(yearPercent, period, cash);
		this.incomeTax = 0;
	}

	@Override
	public void printInfoProduct() {
		System.out.println("Deposit");
		System.out.println("Value of Deposit: "+super.getCash());
		System.out.println("Period         : "+super.getPeriod());
		System.out.println("Interest       : "+super.getYearPercent());
		System.out.println("ID             : "+super.getName());
		System.out.println();
	}
	
	protected double getIncomeInterest(){
		double perc = (double)super.getYearPercent()/12;
		return super.getCash()*perc;
	}
	
	protected double terminatedDeposit(){
		double inc = this.incomeTax+super.getCash();
		this.incomeTax = 0;
		return inc;
	}
	
	protected void insertIncome(double sum){
		this.incomeTax += sum;
	}
	
}
