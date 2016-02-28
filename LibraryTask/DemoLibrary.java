package LibraryTask;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.TreeSet;

public class DemoLibrary {

	public static void main(String[] args) {
		
		LocalDate date = LocalDate.of(1997, 12, 20);
		LocalDate date1 = LocalDate.of(1983, 3, 12);
		LocalDate date2 = LocalDate.of(2005, 6, 30);
		LocalTime now  = LocalTime.now();
		
		TreeSet<LocalDate> dates = new TreeSet<LocalDate>(new Comparator<LocalDate>() {

			@Override
			public int compare(LocalDate o1, LocalDate o2) {
				if(o1.isAfter(o2)){
					return -1;
				}
				return 1;
			}
			
		});
		dates.add(date);
		dates.add(date1);
		dates.add(date2);
		for(LocalDate dat : dates){
			System.out.println(dat);
		}
		System.out.println(now);
		now = now.plusSeconds(3);
		System.out.println(now);
		
		Book one = new Book("Jenata", "Nikolos", "Niko","Dead", date);
		Book one1 = new Book("Voina i Mir", "Nikolos", "Niko","Dead", date1);

		Book one2 = new Book("Trudnost", "Jensi", "Kriminal","Love", date2);
		TextBook bok = new TextBook("Mati", "Prosveta", "Ricakrdo", "Matematika");
		TextBook bok2 = new TextBook("Info", "Prosveta", "Ricakrdo", "Informatika");
		TextBook bok3 = new TextBook("Jminfo", "Prosveta", "Ricakrdo", "Bulgarski");
		
		Library test = new Library();
		test.addBook(one);
		test.addBook(one1);
		test.addBook(one2);
		test.addTextBook(bok);
		test.addTextBook(bok2);
		test.addTextBook(bok3);
		
		test.printCatalogue(Library.Readables.TEXTBOOK);
		new Thread(new Client("Nikorazko", test, one)).start();
		new Thread(new Client("Ivan", test, one1)).start();
		new Thread(new Client("Ivan", test, one2)).start();
		new Thread(new Client("Nikorazko", test, bok)).start();
		new Thread(new Client("Ivan", test, bok2)).start();
		new Thread(new Client("Ivan", test, bok3)).start();
		try {
			test.revision();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static class Client implements Runnable{
		
		private Library own;
		private String name;
		private Readable read;
		
		public Client(String name,Library lib,Readable read) {
			this.own = lib;
			this.name = name;
			this.read = read;
		}
		
		@Override
		public void run() {
			
			this.own.giveRentReadable(this.read);
			try {
				Thread.currentThread().sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.own.takeBackReadable(this.read);
		}
		
		
		
	}
	
}
