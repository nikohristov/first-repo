package Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

public class TestingWrite {

	public static void main(String[] args) throws IOException {
		
		LocalTime first = LocalTime.now();
		LocalTime first2 = first.plusSeconds(10);
		LocalTime first3 = first2.plusSeconds(10);
		LocalTime first4 = first3.plusSeconds(10);
		
		TreeSet<LocalTime> times = new TreeSet<LocalTime>(new Comparator<LocalTime>() {

			@Override
			public int compare(LocalTime arg0, LocalTime arg1) {
				return arg0.compareTo(arg1);
			}
		});
		times.add(first);
		times.add(first4);
		times.add(first2);
		times.add(first3);
		
		File file = new File("someTimes.txt");
		if(!(file.exists())){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter wr = null;
		PrintStream writer = null;
		try {
			wr = new FileWriter(file);
			writer = new PrintStream(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		writer.flush();
		
		
		
		for(LocalTime t : times){
			writer.println(t.toString());
			writer.println("Fuuuu");
		}
		
		
	}

}
