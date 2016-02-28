package GazStation;

import java.time.LocalDate;

public class CarVignette extends Vignette {
	
	private static final double PRICE_DAY = 5;
	private static final int SECONDS_TO_SET = 5;
	private Double price;
	
	public CarVignette(LocalDate date,String duration) throws GazStationException {
		super(date, "red", duration);
		setPrice(duration);
	}

	private void setPrice(String duration) throws GazStationException{
		switch(duration){
		case "day":
			this.price = this.PRICE_DAY;
			break;
		case "mounth":
			this.price = (this.PRICE_DAY*7)*10;
			break;
		case "year":
			this.price = ((this.PRICE_DAY*7)*10)*6;
			break;
			default:
				throw new GazStationException("Type must be only: day/mounth/year !");
		}
	}

	@Override
	public int setOnMobile() {
		return this.SECONDS_TO_SET;
	}

	@Override
	public Double getPrice() {
		return this.price;
	}
	
}
