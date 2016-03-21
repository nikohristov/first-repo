package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import model.User;

public class DBUserDAO {

	private List<User> users;
	
	public DBUserDAO(){
		this.users = this.getAllUsers();
	}

	public List<User> getAllUsers(){
		
		List<User> generate = new ArrayList<User>();
		String statement = "SELECT user_id,username,password FROM "+DBManager.getDbName()+".users";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(statement);
				){
			
			while(rs.next()){
				generate.add(new User(rs.getString(2), rs.getString(3), rs.getInt(1)));
			}
		}catch(SQLException e){
			System.out.println("Problem with generate all users.");
			e.printStackTrace();
		}
		return generate;
	}
	
	public boolean checkIfUserExists(String name){
		for(User u : this.users){
			if(u.getUsername().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	private void addUserInDB(String name,String password){
		String statement = "INSERT INTO "+DBManager.getDbName()+".users (username,password) VALUES (?,?)";
		try(
				PreparedStatement st = (PreparedStatement) DBManager.getInstance().getConnection().prepareStatement(statement);
				){
			st.setString(1, name);
			st.setString(2, password);
			st.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with add to DB user.");
			e.printStackTrace();
		}
	}
	
	public User registerUser(String name,String password){
		this.addUserInDB(name, password);
		User newUser = null;
		String statement = "SELECT user_id FROM "+DBManager.getDbName()+".users WHERE username = \""+name+"\";";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(statement);
				){
			
			while(rs.next()){
				 int id = rs.getInt(1);
				 System.out.println(id);
				 newUser = new User(name,password,id);
			}
			
		}catch(SQLException e){
			System.out.println("Problem with register user");
			e.printStackTrace();
		}
		
		this.users.add(newUser);
		
		return newUser;
	}
	
}
