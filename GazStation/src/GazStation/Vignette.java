package GazStation;

import java.time.LocalDate;

public abstract class Vignette implements Comparable<Vignette>{
	
	private LocalDate date;
	private String color;
	private String duration;
	private LocalDate validateTo;
	
	public Vignette(LocalDate date, String color, String duration) {
		super();
		this.date = date;
		this.color = color;
		this.duration = duration;
		this.setValidate(duration);
	}

	public LocalDate getDate() {
		return date;
	}

	public String getColor() {
		return color;
	}

	public String getDuration() {
		return duration;
	}
	
	public LocalDate getValidate(){
		return this.validateTo;
	}
	
	private void setValidate(String duration){
		switch(duration){
		case "day":
			this.validateTo = this.date.plusDays(1);
		case "mounth":
			this.validateTo = this.date.plusMonths(1);
			break;
		case "year":
			this.validateTo = this.date.plusYears(1);
			break;
			default:
				
		}

	}
	
	@Override
	public String toString() {
		return this.duration + " " + this.color + " " + this.validateTo.toString();
	}
	
	public abstract int setOnMobile();
	
	public abstract Double getPrice();
	
	@Override
	public int compareTo(Vignette o) {
		if(this.getPrice() >= o.getPrice())
			return 1;
		return -1;
	}
}
