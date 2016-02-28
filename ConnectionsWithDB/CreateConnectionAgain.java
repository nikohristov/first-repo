package TryToConnectWithMyDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthScrollBarUI;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;
public class CreateConnectionAgain {

	public static void main(String[] args) {
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("The driver is missing !");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/hr?autoReconnect=true&useSSL=false","root","katikat1");
		} catch (SQLException e) {
			System.out.println("Problem with connetction with DB !");
		}
		
		//Some Statements
		Statement one = null;
		ResultSet rs = null;
		try {
			one = con.createStatement();
			String firstSelect = "SELECT CONCAT(first_name,' ',last_name) AS Name, salary FROM hr.employees";
			 rs = one.executeQuery(firstSelect);
			
			while(rs.next()){
				System.out.println(rs.getString(1)+ " - "+rs.getInt(2));
			}
		} catch (SQLException e) {
			System.out.println("Problem with create a connection !");
			e.printStackTrace();
		}
		
		System.out.println("\t SECOND SELECT ");
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter range of salary :");
		//double range1 = sc.nextDouble();
		//double range2 = sc.nextDouble();
		
		String secondSelect = "SELECT last_name, salary FROM hr.employees WHERE (salary > ? AND salary < ?) ORDER BY salary";
		try {
			PreparedStatement two = (PreparedStatement) con.prepareStatement(secondSelect);
			two.setDouble(1, 5000);
			two.setDouble(2, 5500);
			rs = two.executeQuery();
			
			while(rs.next()){
				System.out.println(rs.getString(1)+ " - "+rs.getInt(2));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("GOOOD \t THIRD SELECT");
		
		String thirdSelect = "CREATE TABLE CountriesTEST( country_id INT auto_increment,country_name varchar(20), PRIMARY KEY(country_id))";
		//try {
			//Statement two = con.createStatement();
			//int n = two.executeUpdate(thirdSelect);
			//System.out.println(n);
	//	} catch (SQLException e) {
			
		//	e.printStackTrace();
	//	}
		
//		String fourSelect = "INSERT INTO hr.countriestest (country_name) VALUES (?)";
//		try {
//			PreparedStatement tree = (PreparedStatement) con.prepareStatement(fourSelect);
//			tree.setString(1, "Bulgaria");
//			int n = tree.executeUpdate();
//			System.out.println(n);
//			tree.setString(1, "Germania");
//			tree.executeUpdate();
//			tree.setString(1, "Serbia");
//			tree.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
								
//		System.out.println("NOW PRINT ALL COUNTRIES:::");
//		try {
//			HashSet<Country> fromTableDB = getAllCountries();
//			for(Country c : fromTableDB){
//				System.out.println(c);
//			}
//		} catch (SQLException e) {
//			System.out.println("Niki pak si  neshto !");
//			e.printStackTrace();
//		}
		
//		System.out.println("GOOD NOW I WILL DROP MY TABLE");
//		String update = " DROP TABLE hr.countriestest";
//		Statement std;
//		try {
//			std = con.createStatement();
//			int n = std.executeUpdate(update);
//			System.out.println(n);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		System.out.println("NOW I WILL PRINT ALL EMPLOYEES WITH THEIR MANAGER AND DEPARTMENT");	
		
//		String empl = "SELECT E.first_name, M.first_name, department_name FROM employees E  JOIN employees M ON (M.employee_id = E.manager_id) JOIN hr.departments ON (E.department_id = hr.departments.department_id);";
//		try {
//			Statement bum = con.createStatement();
//			rs = bum.executeQuery(empl);
//			System.out.println("NAME \t MANAGER \t DEPARTMENT");
//			while(rs.next()){
//				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
		
//		System.out.println("OK NOW I WILL LIST EMPLOYYES IN ONE DEPARTMENT AND THEIR AVG SALARY");
//		String dep = sc.next();
//		String select = "SELECT first_name FROM hr.employees,hr.departments WHERE (hr.employees.department_id = hr.departments.department_id AND hr.departments.department_name = ?);";
//		String select2 = "SELECT AVG(salary) AS AVARAGE FROM hr.employees, hr.departments WHERE(hr.employees.department_id = hr.departments.department_id AND hr.departments.department_name = ?)  GROUP BY hr.employees.department_id;";
//		try {
//			PreparedStatement one1 = (PreparedStatement) con.prepareStatement(select);
//			 one1.setString(1,dep);
//			rs = one1.executeQuery();
//			System.out.println("\t EMPLOYEES:");
//			while(rs.next()){
//				System.out.println(rs.getString(1));
//			}
//			PreparedStatement one2 = (PreparedStatement) con.prepareStatement(select2);
//				one2.setString(1,dep);
//			rs = one2.executeQuery();
//			rs.next();
//			System.out.println("AVARAGE SALARY: "+ rs.getDouble(1));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		finally{
//			try {
//				con.close();
//				one.close();
//				rs.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
		
		try {
			Statement std = con.createStatement();
			String createUser = "ALTER TABLE hr.users ADD FOREIGN KEY (group_id) REFERENCES hr.groups(group_id);";
			int n = std.executeUpdate(createUser);
			System.out.println(n);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addNewUser(new User("Angel"), "banka");
		
	}
	
	public static HashSet<Country> getAllCountries() throws SQLException{
		HashSet<Country> countries = new HashSet<Country>();
		String select = "SELECT DISTINCT country_id,country_name FROM hr.countriestest;";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root","katikat1");
		Statement std = con.createStatement();
		ResultSet rs = std.executeQuery(select);
		
		while(rs.next()){
			countries.add(new Country(rs.getString(2), rs.getInt(1)));
		}
		return countries;
	}
	
	public static void addNewUser(User c,String group){
		
		Connection con = null;
		try{
			String select = "INSERT INTO hr.users (user_name) VALUES (?)";
			 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root","katikat1");
			con.setAutoCommit(false);
			PreparedStatement std = (PreparedStatement) con.prepareStatement(select);
			std.setString(1, c.getName());
			std.executeUpdate();
			String select2 = "UPDATE hr.users,hr.groups SET hr.users.group_id = hr.groups.group_id WHERE (hr.users.user_name = ? AND hr.groups.group_name = ?);";
			std = (PreparedStatement) con.prepareStatement(select2);
			std.setString(1, c.getName());
			
			std.setString(2, group);
			
			std.executeUpdate();
			con.commit();
		}catch(SQLException e){
			e.getMessage();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void addNewGroup(Group p) throws SQLException{
		String select = "INSERT INTO hr.groups (group_name) VALUES (?);";
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/?autoReconnect=true&useSSL=false", "root","katikat1");
		PreparedStatement std = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(select);
		std.setString(1, p.getName());
		std.executeUpdate();
	}
	
}
