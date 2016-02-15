package MusicalInstruments;

public class MusicalInstrument implements Comparable<MusicalInstrument>{
	
	private String name;
	private Integer price;
	
	public MusicalInstrument(String name, int price) {
		this.name = name;
		this.price = price;
	}
	
	protected String getName(){
		return this.name;
	}
	
	protected Integer getPrice(){
		return this.price;
	}

	@Override
	public int compareTo(MusicalInstrument o) {
		return this.name.compareTo(o.name);
	}

	protected void printInfoInstrument(){
		System.out.print(this.name+" price: "+this.price+"$");
	}
	
	
}
