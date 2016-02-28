package LibraryTask;

public abstract class Readable {
	
	private String name;
	private String publishingHouse;
	
	public Readable(String name,String publishingHouse) {
		this.name = name;
		this.publishingHouse = publishingHouse;
	}

	public String getName() {
		return name;
	}

	public String getPublishingHouse() {
		return publishingHouse;
	}
	
	
	
}
