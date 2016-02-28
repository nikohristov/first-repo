
public abstract class Person {
	
	private String name;
	private String telNumber;
	
	public Person(String name,String telNumber) {
		this.name = name;
		this.telNumber = telNumber;
	}

	public String getName() {
		return name;
	}

	public String getTelNumber() {
		return telNumber;
	}
	
	
}
