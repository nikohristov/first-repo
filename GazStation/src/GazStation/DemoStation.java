package GazStation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class DemoStation {
	
	public static PrintStream writer;
	
	public static void main(String[] args) {
		
		File file = new File("Station.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			writer = new PrintStream(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CarMobile audi = new CarMobile("A4", 2012);
		CarMobile audi1 = new CarMobile("A3", 2012);
		CarMobile audi2 = new CarMobile("A5", 2012);
		CarMobile audi3 = new CarMobile("A6", 2012);
		CarMobile audi4 = new CarMobile("A7", 2012);
		
		TruckMobile mer = new TruckMobile("M", 2005);
		TruckMobile mer2 = new TruckMobile("M", 2005);
		TruckMobile mer3 = new TruckMobile("M", 2005);
		TruckMobile mer4= new TruckMobile("M", 2005);
		
		BusMobile iveco = new BusMobile("iveco", 2013);
		BusMobile iveco2 = new BusMobile("iveco", 2013);
		BusMobile iveco3 = new BusMobile("iveco", 2013);
		
		try {
			
			GazStation omv = new GazStation();
			omv.printAllVignettesInStation();
			Driver bro = new Driver(omv, "Nikolay", 100000);
			//add to first driver
			bro.addMobile(audi);
			bro.addMobile(audi2);
			bro.addMobile(audi3);
			bro.addMobile(mer2);
			bro.addMobile(mer);
			bro.addMobile(audi);
			bro.addMobile(iveco);
			Driver two = new Driver(omv, "Joro", 500000);
			//add to second driver
			two.addMobile(audi1);
			two.addMobile(audi4);
			two.addMobile(mer3);
			two.addMobile(mer4);
			two.addMobile(iveco2);
			two.addMobile(iveco3);
			new Thread(bro).start();
			new Thread(two).start();
		} catch (GazStationException e) {
			e.printStackTrace();
		}
		
	}

}
