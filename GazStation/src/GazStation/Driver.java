package GazStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Driver implements Runnable{
	
	private GazStation station;
	private String name;
	private double money;
	private ArrayList<Mobile> mobiles;
	
	public Driver(GazStation station, String name, double money) {
		super();
		this.station = station;
		this.name = name;
		this.money = money;
		this.mobiles = new ArrayList<Mobile>();
	}

	public GazStation getStation() {
		return station;
	}

	public String getName() {
		return name;
	}

	public double getMoney() {
		return money;
	}

	public ArrayList<Mobile> getMobiles() {
		return mobiles;
	}
	
	private void buyVignette(double price) throws GazStationException{
		if(this.money < price)
			throw new GazStationException("Driver "+this.getName()+" dont have enoughf money !");
		this.money -= price;
	}
	
	public void buyForAllMobile() throws GazStationException{
		Random r = new Random();
		for(Mobile m : this.mobiles){
			System.out.println(m);
			if(!m.haveVintage()){
				m.setVintage(this.station.sellVignette(m, GazStation.duration[r.nextInt(GazStation.duration.length)]));
				this.buyVignette(m.getVignette().getPrice());
			}
		}
	}
	
	public void addMobile(Mobile m){
		this.mobiles.add(m);
	}
	
	public void printAllOffDuration(LocalDate date){
		ArrayList<Mobile> timeleft = new ArrayList<Mobile>();
		for(Mobile m : this.mobiles){
			if(m.haveVintage()){
				if(m.getVignette().getValidate().isBefore(date)){
					timeleft.add(m);
				}
			}
		}
		DemoStation.writer.println("List with all mobiles need rebuy vignette for driver "+this.getName());
		for(Mobile m : timeleft){
			DemoStation.writer.println(m);
		}
	}

	@Override
	public void run() {
		
		try {
			this.buyForAllMobile();
			Thread.currentThread().sleep(2000);
			this.printAllOffDuration(LocalDate.now().plusYears(2));
		} catch (GazStationException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
