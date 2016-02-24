package Company;

public class Employee implements Comparable<Employee>{
	
	private static int ID = 0;
	private String name;
	private int IDNumber;
	private Integer age;
	private Double salary;
	
	public Employee(String name,int age,double salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.IDNumber = ++Employee.ID;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Double getSalary() {
		return salary;
	}
	
	@Override
	public String toString() {
		return this.name+" - "+this.age+" - "+this.salary+" salary.";
	}

	@Override
	public int compareTo(Employee o) {
		if(this.name.compareTo(o.name) == 0){
			return this.age.compareTo(o.age);
		}
		return this.name.compareTo(o.name);
	}
	
}
