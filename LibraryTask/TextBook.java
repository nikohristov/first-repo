package LibraryTask;

public class TextBook extends RentReadable implements Comparable<TextBook>{
	
	private String author;
	private String theme;
	
	public TextBook(String name, String publishingHouse,String author,String theme) {
		super(name, publishingHouse,3);
		this.author = author;
		this.theme = theme;
	}

	public String getAuthor() {
		return author;
	}

	public String getTheme() {
		return theme;
	}

	@Override
	public int compareTo(TextBook o) {
		return this.getName().compareTo(o.getName());
	}
	
	@Override
	public String toString() {
		return this.getName() + " author: "+this.author;
	}

	@Override
	public void run() {
		try {
			Thread.currentThread().sleep(Library.MAX_OF_TEXTBOOK);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(!(this.isBack())){
			this.setPrice(this.getPrice()+this.getPrice()*0.01);
			System.out.println("Price for "+this.getName()+" increse to " +this.getPrice() );
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

}
