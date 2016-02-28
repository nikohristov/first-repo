package LibraryTask;

import java.time.LocalTime;
import java.util.TreeMap;

public abstract class RentReadable extends Readable implements IRentable,Runnable{
	
	private double price;
	private boolean isBack;
	private TreeMap<LocalTime,LocalTime> history;
	private LocalTime lastRentDate;

	public RentReadable(String name, String publishingHouse,double price) {
		super(name, publishingHouse);
		this.isBack = true;
		this.price = price;
		this.history = new TreeMap<LocalTime,LocalTime>();
		this.lastRentDate = null;
	}

	@Override
	public void setRentDate(LocalTime date) {
		this.history.put(date, null);
		this.lastRentDate = date;
	}

	public LocalTime getLastRentDate() {
		return lastRentDate;
	}

	public TreeMap<LocalTime, LocalTime> getHistory() {
		return history;
	}
	
	public void setIsBack(boolean b){
		this.isBack = b;
	}

	public double getPrice() {
		return price;
	}

	public boolean isBack() {
		return isBack;
	}

	public void setPrice(double price){
		this.price = price;
	}
	
	
	
}
