package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
	
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost:3306/?useSSL=false";
	
	final static String USER = "root";
	final static String PASS = "damn";
	private final static String DB_NAME = "pl"; // Date Base name
	
	private static DBManager instance;
	
	private Connection conn = null;
	Statement stmt = null;
	
	private DBManager() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (ClassNotFoundException e) {
			System.out.println("Problem loading driver");
		} catch (SQLException e) {
			System.out.println("Problem with connect with MySql");
		}
		
		this.createSchema(conn);
		this.addTablesToSchema(conn);
	}
	
	public static synchronized DBManager getInstance(){
		if(instance == null){
			instance = new DBManager();
		}
		return instance;
	}
	
	private void addTablesToSchema(Connection con){
		Statement st = null;
		
		String user = "CREATE TABLE IF NOT EXISTS " + this.getDbName() +"."+"users"+" (user_id int PRIMARY KEY AUTO_INCREMENT, username VARCHAR(25) UNIQUE NOT NULL, password VARCHAR(25) NOT NULL);";
		String film = "CREATE TABLE IF NOT EXISTS " + this.getDbName() +"."+"films"+" (title VARCHAR(40) NOT NULL, date varchar(50) NOT NULL, genre VARCHAR(50) NOT NULL, director VARCHAR(40) NOT NULL, user_id INT,comment VARCHAR(250), CONSTRAINT pk_filmID PRIMARY KEY (title,date));";
		String favFilms = "CREATE TABLE IF NOT EXISTS " + this.getDbName() + "."+"favourite_films"+" (user_id int NOT NULL, film_title VARCHAR(40) UNIQUE NOT NULL,film_date VARCHAR(50) NOT NULL, FOREIGN KEY(user_id) REFERENCES "+this.getDbName()+"."+"users(user_id), FOREIGN KEY(film_title) REFERENCES "+this.getDbName()+".films(title) ON DELETE CASCADE);";
		String watchedFilm = "CREATE TABLE IF NOT EXISTS "+this.getDbName()+ "."+"watched_films"+" (user_id int NOT NULL, film_title VARCHAR(40) UNIQUE NOT NULL,film_date VARCHAR(50) NOT NULL, FOREIGN KEY(user_id) REFERENCES "+this.getDbName()+"."+"users(user_id), FOREIGN KEY(film_title) REFERENCES "+this.getDbName()+".films(title) ON DELETE CASCADE);";
		
		this.createTable(conn, st, user);
		this.createTable(conn, st, film);
		this.createTable(conn, st, favFilms);
		this.createTable(conn, st, watchedFilm);
		
	}
	private void createSchema(Connection conn){
		String createSchema = "CREATE SCHEMA IF NOT EXISTS `" + getDbName() + "` DEFAULT CHARACTER SET utf8";
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(createSchema);
		} catch (SQLException e) {
			System.out.println("Problem with create schema");
		}
	}
	
	public static String getDbName(){
		return DB_NAME;
	}
	
	public Connection getConnection(){
		return this.conn;
	}
	
	public void closeConnection(){
		try {
			this.conn.close();
		} catch (SQLException e) {
			System.out.println("Problem with close connection");
			e.printStackTrace();
		}
	}
	
	private void createTable(Connection conn, Statement st,String statement){
		try {
			st = conn.createStatement();
			st.executeUpdate(statement);
		} catch (SQLException e) {
			System.out.println("Problem with create table: ");
			e.printStackTrace();
		}
	}
}
