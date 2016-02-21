
public abstract class Provider implements Comparable<Provider>{
	
	private String name;
	private String address;
	private String workingTime;
	private int percentDiscount;
	
	public Provider(String name,String address,String workingTime,int percent) {
		this.name = name;
		this.address = address;
		this.workingTime = workingTime;
		this.percentDiscount = percent;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getWorkingTime() {
		return workingTime;
	}

	public int getPercentDiscount() {
		return percentDiscount;
	}
	
	protected abstract double makeDiscount(int amount);
	
	@Override
	public int compareTo(Provider o) {
		return this.name.compareTo(o.name);
	}
	
}
