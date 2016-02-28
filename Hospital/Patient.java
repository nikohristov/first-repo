
public class Patient extends Person{
	
	private int age;
	private boolean isMale;

	public Patient(String name, String telNumber,int age,boolean isMale) {
		super(name, telNumber);
		this.age = age;
		this.isMale = isMale;
	}

	public int getAge() {
		return age;
	}

	public boolean isMale() {
		return isMale;
	}
	
	

}
