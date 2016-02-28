package LibraryTask;

import java.time.LocalDate;

public class Magazine extends Readable implements Comparable<Magazine>{
	
	private String category;
	private Integer numOfProd;
	private LocalDate date;

	public Magazine(String name, String publishingHouse,String category,int numOfProd,LocalDate date) {
		super(name, publishingHouse);
		this.category = category;
		this.numOfProd = numOfProd;
		this.date = date;
		
	}

	public String getCategory() {
		return category;
	}

	public Integer getNumOfProd() {
		return numOfProd;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public int compareTo(Magazine o) {
		if(this.getName().compareTo(o.getName()) == 0){
			return this.numOfProd.compareTo(o.numOfProd);
		}
		return this.getName().compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return this.getName() + " - " + " br: "+this.numOfProd;
	}
	

}
