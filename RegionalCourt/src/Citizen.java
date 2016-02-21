
public abstract class Citizen implements Comparable<Citizen>{
	
	private String name;
	private String address;
	private Integer age;
	
	public Citizen(String name,String address,int age) {
		this.name = name;
		this.address = address;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public int getAge() {
		return age;
	}
	
	@Override
	public int compareTo(Citizen o) {
		if(this.name.compareTo(o.name) == 0){
			return this.age.compareTo(o.age);
		}else
			return name.compareTo(o.name);
	}
	
}
