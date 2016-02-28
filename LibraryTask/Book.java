package LibraryTask;

import java.time.LocalDate;

public class Book extends RentReadable implements Comparable<Book>{
	
	private String author;
	private String genre;
	private LocalDate date;
	
	public Book(String name, String publishingHouse,String author,String genre,LocalDate date) {
		super(name, publishingHouse,2);
		this.author = author;
		this.genre = genre;
		this.date = date;
	}

	public String getAuthor() {
		return author;
	}

	public String getGenre() {
		return genre;
	}

	public LocalDate getDate() {
		return date;
	}

	@Override
	public int compareTo(Book o) {
		if(this.getName().equals(o.getName()))
			return 0;
		if(this.date.isAfter(o.date)){
			return -1;
		}
		return 1;
	}
	
	@Override
	public String toString() {
		return this.getName() + " author: "+this.author;
	}

	@Override
	public void run() {
		
		try {
			Thread.currentThread().sleep(Library.MAX_OF_BOOK);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!(this.isBack())){
			this.setPrice(this.getPrice()+this.getPrice()*0.01);
			System.out.println("Price for "+this.getName()+" increse to " +this.getPrice());
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
		return;
	}
	
}
