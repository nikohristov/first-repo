package Company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class Company {
	
	private String name;
	private HashMap<String,TreeSet<Employee>> employees;
	
	public Company(String name) {
		this.name = name;
		this.employees = new HashMap<String,TreeSet<Employee>>();
	}

	public String getName() {
		return name;
	}

	public HashMap<String, TreeSet<Employee>> getEmployees() {
		return employees;
	}
	
	protected void addEmployee(String string,Employee e){
		if(!(this.employees.containsKey(string))){
			this.employees.put(string, new TreeSet<Employee>());
		}
		this.employees.get(string).add(e);
	}
	
	protected void printDepartmentsWithTheirEmployees(){
		for(Map.Entry<String, TreeSet<Employee>> map : this.employees.entrySet()){
			System.out.println("\t"+map.getKey());
			for(Employee e : map.getValue()){
				System.out.println(e);
			}
		}
	}
	
	protected void printAllEmployeesSortedByAge(){
		TreeMap<String,TreeSet<Employee>> sortedByAge = new TreeMap<String,TreeSet<Employee>>();
		for(Map.Entry<String, TreeSet<Employee>> map : this.employees.entrySet()){
			if(!(sortedByAge.containsKey(map.getKey()))){
				sortedByAge.put(map.getKey(), new TreeSet<Employee>(new Comparator<Employee>() {
		
					@Override
					public int compare(Employee o1, Employee o2) {
						return o1.getAge().compareTo(o2.getAge());
					}
				}));
			}
			sortedByAge.get(map.getKey()).addAll(map.getValue());
		}
		for(Map.Entry<String, TreeSet<Employee>> map : sortedByAge.entrySet()){
			System.out.println("\t"+map.getKey());
			for(Employee e : map.getValue()){
				System.out.println(e);
			}
		}
	}
	
	protected void printAllEmployeesSortedByTheirSalary(){
		
		for(Map.Entry<String , TreeSet<Employee>> map : this.employees.entrySet()){
			System.out.println("\t"+map.getKey());
			ArrayList<Employee> sortedAges = new ArrayList<Employee>(map.getValue());
			Collections.sort(sortedAges, new Comparator<Employee>() {
				@Override
				public int compare(Employee o1, Employee o2) {
					if(o1.getSalary() > o2.getSalary())
						return -1;
					else 
						return 1;
				}
				
			});
			for(Employee e : sortedAges){
				System.out.println(e);
			}
		}
	}
}
