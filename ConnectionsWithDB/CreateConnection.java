package TryToConnectWithMyDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.jdbc.PreparedStatement;


public class CreateConnection {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
			Connection con = null;
			
		try {
			 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hr?autoReconnect=true&useSSL=false", "root", "katikat1");
			Statement std = con.createStatement();
			ResultSet rs = std.executeQuery("SELECT first_name,salary FROM hr.employees WHERE salary > 10000");
			while(rs.next()){
				System.out.println("row: ");
				System.out.print(rs.getString(1)+" "+rs.getInt(2)+" ");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println();
		Scanner sc = new Scanner(System.in);
		System.out.println("enter interval of values");
		int first = sc.nextInt();
		int second = sc.nextInt();
		String sqlInsert = "SELECT last_name,salary FROM hr.employees WHERE salary >= ? AND salary <= ?";
		
			
			PreparedStatement st = null;
			ResultSet rs = null;
			try {
				st = (PreparedStatement) con.prepareStatement(sqlInsert);
				st.setInt(1, first);
				st.setInt(2, second);
				rs = st.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
				
			try {
				while(rs.next()){
					System.out.print("row: ");
					System.out.print(rs.getString(1)+" "+rs.getInt(2));
					System.out.println();
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					st.close();
					rs.close();
					con.close();
				}catch(SQLException e){
					e.getMessage();
				}
			}
		
		
		
	}
	
}
