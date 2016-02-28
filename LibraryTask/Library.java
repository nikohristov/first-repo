package LibraryTask;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Library {
	
	enum Readables  {BOOK,TEXTBOOK,MAGAZINE}
	
	public static final int MAX_OF_BOOK = 8000;
	public static final int MAX_OF_TEXTBOOK = 4000;
	private HashMap<String,TreeMap<String,TreeSet<Readable>>> catalogue;
	private HashMap<IRentable,HashMap<LocalTime,LocalTime>> rentHistory;
	
	public Library() {
		
		this.catalogue = new HashMap<String,TreeMap<String, TreeSet<Readable>>>();
		this.rentHistory = new HashMap<IRentable,HashMap<LocalTime,LocalTime>>();
	}
	
	public void addBook(Book book){
		String genre = book.getGenre();
		if(!(this.catalogue.containsKey("Book"))){
			this.catalogue.put("Book", new TreeMap<String,TreeSet<Readable>>());
		}
		if(!(this.catalogue.get("Book").containsKey(genre))){
			this.catalogue.get("Book").put(genre, new TreeSet<Readable>());
		}
		this.catalogue.get("Book").get(genre).add(book);
	}
	
	public void addTextBook(TextBook textBook){
		String theme = textBook.getTheme();
		if(!(this.catalogue.containsKey("TextBook"))){
			this.catalogue.put("TextBook", new TreeMap<String,TreeSet<Readable>>());
		}
		if(!(this.catalogue.get("TextBook").containsKey(theme))){
			this.catalogue.get("TextBook").put(theme, new TreeSet<Readable>());
		}
		this.catalogue.get("TextBook").get(theme).add(textBook);
	}
	
	public void addMagazine(Magazine magazine){
		String cat = magazine.getCategory();
		if(!(this.catalogue.containsKey("Magazine"))){
			this.catalogue.put("Magazine", new TreeMap<String,TreeSet<Readable>>());
		}
		if(!(this.catalogue.get("Magazine").containsKey(cat))){
			this.catalogue.get("Magazine").put(cat, new TreeSet<Readable>());
		}
		this.catalogue.get("Book").get(cat).add(magazine);
	}
	
	public void printCatalogue(Readables e){
		switch(e){
		case BOOK:
			System.out.println("\tBOOKS:");
			for(Map.Entry<String,TreeMap<String,TreeSet<Readable>>> map : this.catalogue.entrySet()){
				if(map.getKey().equals("Book")){
					for(Map.Entry<String, TreeSet<Readable>> bookMap : map.getValue().entrySet()){
						System.out.println(bookMap.getKey());
						for(Readable c : bookMap.getValue()){
							System.out.println("\t"+c);
						}
					}
					return;
				}
			}
		case TEXTBOOK:
			System.out.println("\tTEXTBOOKS");
			for(Map.Entry<String,TreeMap<String, TreeSet<Readable>>> map : this.catalogue.entrySet()){
				if(map.getKey().equals("TextBook")){
					for(Map.Entry<String, TreeSet<Readable>> textMap : map.getValue().entrySet()){
						System.out.println(textMap.getKey());
						for(Readable c : textMap.getValue()){
							System.out.println("\t"+c);
						}
					}
					return;
				}
			}
		case MAGAZINE:
			System.out.println("\tMAGAZINES");
			for(Map.Entry<String, TreeMap<String,TreeSet<Readable>>> map : this.catalogue.entrySet()){
				if(map.getKey().equals("Magazine")){
					for(Map.Entry<String, TreeSet<Readable>> magMap : map.getValue().entrySet()){
						System.out.println(magMap.getKey());
						for(Readable c : magMap.getValue()){
							System.out.println("\t"+c);
						}
					}
					return;
				}
			}
			default:
				break;
		}
		
	}
		
	public synchronized void giveRentReadable(Readable c){
		Thread read=null;
		try{
			 read = new Thread((Runnable) c);
		}catch(ClassCastException e){
			System.out.println("You may give only Books and Textbooks for rent !");
			return;
		}
		if(c instanceof Book){
			if(!((((Book) c)).isBack())){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				((Book)c).setIsBack(false);
				LocalTime time = LocalTime.now();
				((IRentable)c).setRentDate(time);
				if(!(this.rentHistory.containsKey(c))){
					this.rentHistory.put((IRentable) c, new HashMap<LocalTime,LocalTime>());
				}
				this.rentHistory.get(c).put(time, time.plusSeconds(MAX_OF_BOOK));
				read.start();
		}else if(c instanceof TextBook){
			if(!((((TextBook) c)).isBack())){
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				((TextBook)c).setIsBack(false);
				LocalTime time = LocalTime.now();
				((IRentable)c).setRentDate(time);
				if(!(this.rentHistory.containsKey(c))){
					this.rentHistory.put((IRentable) c, new HashMap<LocalTime,LocalTime>());
				}
				this.rentHistory.get(c).put(time, time.plusSeconds(MAX_OF_TEXTBOOK));
				read.start();
		}
		
	}


	public synchronized void takeBackReadable(Readable read) {
			((RentReadable)read).setIsBack(true);
			System.out.println("The amount that you must pay is "+((RentReadable)read).getPrice());
			if(read instanceof Book){
				((RentReadable)read).setPrice(2);
			}else{
				((RentReadable)read).setPrice(3);
			}
			for(Map.Entry<LocalTime, LocalTime> map : ((RentReadable)read).getHistory().entrySet()){
				if(map.getKey().equals(((RentReadable)read).getLastRentDate())){
					((RentReadable)read).getHistory().put(map.getKey(), LocalTime.now());
				}
			}
			notify();
	}
	
	public void printTaken(){
		
	}
	
	public void revision() throws IOException{
		int freeBooks = 0;
		for(Map.Entry<String, TreeMap<String,TreeSet<Readable>>> map : this.catalogue.entrySet()){
			for(Map.Entry<String, TreeSet<Readable>> readMap : map.getValue().entrySet()){
				for(Readable c : readMap.getValue()){
					if(c instanceof RentReadable){
						if(((RentReadable)c).isBack() == true){
							freeBooks++;
							System.out.println(c);
						}
					}
				}
			}
		}
		System.out.println("Avaiwable readables: "+freeBooks);
		
		//rent readables
		ArrayList<Map.Entry<IRentable, HashMap<LocalTime, LocalTime>>> sortedByDate = new ArrayList<>(this.rentHistory.entrySet());
		Collections.sort(sortedByDate,new Comparator<Map.Entry<IRentable, HashMap<LocalTime, LocalTime>>>() {

			@Override
			public int compare(Entry<IRentable, HashMap<LocalTime, LocalTime>> o1,
					Entry<IRentable, HashMap<LocalTime, LocalTime>> o2) {
				return o1.getValue().get(o1.getValue().size()).compareTo(o2.getValue().get(o2.getValue().size()));
			}
		});
		File file = new File("AllRent.txt");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		FileWriter write = null;
		try {
			 write = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Map.Entry<IRentable, HashMap<LocalTime, LocalTime>> map : sortedByDate){

				write.write(map.getKey() + " "+map.getValue().get(map.getValue().size()));
				System.out.println(sortedByDate.size());
				write.write(sortedByDate.size());
				write.write('t');
			
		}
		write.close();
	}
	
}
