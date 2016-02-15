package Bank;

import java.util.ArrayList;

import javax.swing.plaf.synth.SynthScrollBarUI;

public class Bank {
	
	private String name;
	private String address;
	private ArrayList<BankProduct> products;
	private double currentMoney;
	private double reserve;
	
	public Bank(String name, String address) {
		this.name = name;
		this.address = address;
		this.products = new ArrayList<BankProduct>();
		this.currentMoney = 20000;
		this.reserve = 2000;
	}
	
	protected Deposit takeDeposit(double sum, int period){
		Deposit dep = new Deposit((byte)60, period, sum);
		this.products.add(dep);
		this.currentMoney += sum*0.1;
		this.reserve += sum*0.9;
		return dep;
	}
	
	protected double getDeposits(){
		double sum = 0;
		for(int i=0; i<this.products.size(); i++){
			if(this.products.get(i) instanceof Deposit){
				sum = sum + this.products.get(i).getCash();
			}
		}
		return sum;
	}
	
	protected Credit giveCredit(double sum, int period){
		if(sum > this.reserve){
			throw new IllegalArgumentException("No money in the bank !");
		}else if((this.currentMoney - sum) < (this.getDeposits()*0.1)){
			throw new IllegalArgumentException("Not enoughf money in the bank !");
		}
		Credit cred = new Credit((byte)100, period, sum);
		this.products.add(cred);
		this.currentMoney -= sum;
		return cred;
	}
	
	protected void insertTaxes(double sum){
		this.currentMoney += sum;
	}
	
	public ArrayList<BankProduct> getProducts() {
		return products;
	}

	protected void payInterest(){
		for(int i=0; i<this.products.size(); i++){
			if(this.products.get(i) instanceof Deposit){
				double price = ((Deposit)this.products.get(i)).getIncomeInterest();
				((Deposit)this.products.get(i)).insertIncome(price);
				this.currentMoney -= price;
			}
		}
	}
	
	protected void infoBank(){
		System.out.println("Name: "+this.name);
		System.out.println("Adress: "+this.address);
		System.out.println("Balance: "+this.currentMoney);
		System.out.println("CashReserve: "+this.reserve);
		System.out.println();
	}
}
