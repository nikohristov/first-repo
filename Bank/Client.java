package Bank;

import java.util.ArrayList;

public class Client {
	
	private String name;
	private String address;
	private double money;
	private double salary;
	private ArrayList<Deposit> deposits;
	private ArrayList<Credit> credits;
	
	public Client(String name, String address, double money, double salary) {
		this.name = name;
		this.address = address;
		this.money = money;
		this.salary = salary;
		
		this.deposits = new ArrayList<Deposit>();
		this.credits = new ArrayList<Credit>();
	}
	
	protected void printInfoOfClient(){
		System.out.println("Name   :"+this.name);
		System.out.println("Address:"+this.address);
		System.out.println("Products:   ");
		for(int i=0; i<this.deposits.size(); i++){
			this.deposits.get(i).printInfoProduct();
		}
		for(int i=0; i<this.deposits.size(); i++){
			this.credits.get(i).printInfoProduct();
		}
	}
	
	protected void deposit(double sum,int period,Bank b){
		if(sum > this.money){
			throw new IllegalArgumentException("Not enoughf money !");
		}
		Deposit dep = (Deposit) b.takeDeposit(sum, period);
		this.deposits.add(dep);
		this.money -= sum;
	}
	
	protected void credit(double sum, int period, Bank b) throws IllegalArgumentException{
		if(sum < (this.getCreditTax()*0.5)){
			throw new IllegalArgumentException("You dont have enoufgh income !");
		}
		Credit cred = (Credit)b.giveCredit(sum, period);
		this.credits.add(cred);
		this.money += sum;
	}
	
	protected double getCreditTax(){
		double sum = 0;
		for(int i=0; i < this.credits.size(); i++){
			sum += this.credits.get(i).getMounthlyTax();
		}
		return sum;
	}
	
	protected void payCreditTax(Bank b){
		double sum = this.getCreditTax();
		b.insertTaxes(sum);
		this.money -= sum;
	}

	public double getMoney() {
		return money;
	}
}
