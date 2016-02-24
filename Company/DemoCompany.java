package Company;

import java.util.HashMap;
import java.util.TreeSet;

public class DemoCompany {
		private String[] DEPARTMENTS = {"SALES","FINANCE","MARKETING"};
	public static void main(String[] args) {
		Employee nik = new Employee("Nikolay", 21, 800);
		Employee ivan = new Employee("Ivan", 24, 1200);
		Employee kat = new Employee("Katka", 27, 1800);
		Employee hris = new Employee("Hrisi", 21, 1300.5);
		Employee radk = new Employee("Radka", 19, 455);
		Employee ico = new Employee("Hristo", 34, 2722);
		Employee sasho = new Employee("Sasho", 37, 333);
		
		Company one = new Company("Adireli");
		one.addEmployee("SALES", nik);
		one.addEmployee("FINANCE", ivan);
		one.addEmployee("SALES", kat);
		one.addEmployee("FINANCE", hris);
		one.addEmployee("MARKETING", radk);
		one.addEmployee("FINANCE", ico);
		one.addEmployee("MARKETING", sasho);
		one.addEmployee("MARKETING", nik);
		
		one.printDepartmentsWithTheirEmployees();
		System.out.println("\tSorted by age");
		one.printAllEmployeesSortedByAge();
		System.out.println("\tSORTET BY SALARY");
		System.out.println();
		one.printAllEmployeesSortedByTheirSalary();
		System.out.println();
		printedAllEmployeesOfCompany(one.getEmployees());
	}

	public static void printedAllEmployeesOfCompany(HashMap<String,TreeSet<Employee>> map){
		TreeSet<Employee> sortedNames = new TreeSet<Employee>();
		for(TreeSet<Employee> e : map.values()){
			sortedNames.addAll(e);
		}
		System.out.println("LIST WITH ALL EMPLOYEES");
		for(Employee e : sortedNames){
			System.out.println("\t"+e);
		}
	}
	
}
