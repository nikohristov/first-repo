package GazStation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.TreeSet;

public class GazStation {
	
	public static final String[] duration = {"day","mounth","year"};
	private static String[] type =  {"car","bus","truck"};
	
	private TreeSet<Vignette> vignettes;
	private double money;
	
	Random r = new Random();
	
	public GazStation() throws GazStationException {
		this.money = 1000;
		this.vignettes = new TreeSet<Vignette>();
		for(int i=0; i<100; i++){
			int n = r.nextInt(this.type.length);
			String type = this.type[n];
			switch(type){
			case "car":
				CarVignette shit = new CarVignette(LocalDate.now(), this.duration[r.nextInt(this.duration.length)]);
				this.vignettes.add(shit); 
				break;
			case "bus":
				BusVignette bus = new BusVignette(LocalDate.now(), this.duration[r.nextInt(this.duration.length)]);
				this.vignettes.add(bus);
			case "truck":
				TruckVignette truck = new TruckVignette(LocalDate.now(), this.duration[r.nextInt(this.duration.length)]);
				this.vignettes.add(truck);
				break;
			}
		}
	}
	
	public void printAllVignettesInStation(){
		for(Vignette vignette : this.vignettes){
			DemoStation.writer.println(vignette);
		}
	}
	
	public synchronized Vignette sellVignette(Mobile mobile,String duration){
		if(mobile instanceof CarMobile){
			for(Vignette c : this.vignettes){
				if(c.getDuration().equals(duration) && c instanceof CarVignette){
					this.money += c.getPrice();
					this.vignettes.remove(c);
					DemoStation.writer.println(c+ " - sold.");
					return c;
				}
			}
		}else if(mobile instanceof BusMobile){
			for(Vignette c : this.vignettes){
				if(c.getDuration().equals(duration) && c instanceof BusVignette){
					this.money += c.getPrice();
					this.vignettes.remove(c);
					DemoStation.writer.println(c+ " - sold.");
					return c;
				}
			}
		}else
			for(Vignette c : this.vignettes){
				if(c.getDuration().equals(duration) && c instanceof TruckVignette){
					this.money += c.getPrice();
					this.vignettes.remove(c);
					DemoStation.writer.println(c+ " - sold.");
					return c;
				}
			}
		return null;
	}
	
	
	
}
