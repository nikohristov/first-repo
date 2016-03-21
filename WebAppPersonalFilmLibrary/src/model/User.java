package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.mysql.jdbc.Statement;

import db.DBManager;

public class User {
	
	private String username;
	private String password;
	private int uniqueID;
	private HashSet<Film> favouriteFilms;
	private HashSet<Film> watchedFilms;
	private HashSet<Film> uploadedFilms;
	
	public User(String username,String password, int uniqueID){
		this.username = username;
		this.password = password;
		this.uniqueID = uniqueID;
		this.favouriteFilms = new HashSet<>();
		this.watchedFilms = new HashSet<>();
		this.uploadedFilms = new HashSet<>();
	}
	
	
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public int getUniqueID() {
		return uniqueID;
	}
	
	public HashSet<Film> getAllFilmsForUser(){
		this.generateAllUploadFilmByUser();
		this.generateAllFavouriteForUser();
		this.generateAllWatchedForUser();
		
		HashSet<Film> allFilms = new HashSet<Film>();
		allFilms.addAll(this.uploadedFilms);
		for(Film f : this.favouriteFilms){
			boolean flag = true;
			for(Film two : allFilms){
				if(f.getTitle().equals(two.getTitle()) && f.getDate().equals(two.getDate())){
					two.setIsFavourite(true);
					flag=false;
					break;
				}
			}
			if(flag == true){
				allFilms.add(f);
			}
		}
		
		for(Film f : allFilms){
			if(this.watchedFilms.contains(f)){
				f.setIsWatched(true);
			}
		}
		
		for(Film f : this.watchedFilms){
			if(!allFilms.contains(f)){
				allFilms.add(f);
			}
		}
		
		for(Film f : allFilms){
			System.out.println(f.getTitle()+" "+f.getisFavourite()+" "+f.isWatched()+" "+f.isUpload());
		}
		
		return allFilms;
	}
	
	private void generateAllFavouriteForUser(){
		String statement = "SELECT title,date,genre,director,comment FROM "+DBManager.getDbName()+".films JOIN (SELECT film_title,film_date FROM pl.favourite_films WHERE user_id = "+this.uniqueID+") E ON (pl.films.title = pl.E.film_title AND pl.films.date = pl.E.film_date);";
		try( 
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(statement);
				){
			
			while(rs.next()){
				Film film = new Film(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"none",0,false,true,false);
				if(rs.getString(5) != null)
				film.setComment(rs.getString(5));
				this.favouriteFilms.add(film);
			}
			
		}catch(SQLException e){
			System.out.println("Problem with adding favourite films");
			e.printStackTrace();
		}
	}
	
	public HashSet<Film> getFavouriteFIlms(){
		return this.favouriteFilms;
	}
	
	private void generateAllWatchedForUser(){
		String statement = "SELECT title,date,genre,director FROM "+DBManager.getDbName()+".films JOIN (SELECT film_title,film_date FROM pl.watched_films WHERE user_id = "+this.uniqueID+") E ON (pl.films.title = pl.E.film_title AND pl.films.date = pl.E.film_date);";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(statement);
				){
			
				while(rs.next()){
					this.watchedFilms.add(new Film(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"none",0,true,false,false));
				}
			
		}catch(SQLException e){
			System.out.println("Problem with loading all watched films");
			e.printStackTrace();
		}
		
	}
	
	private void generateAllUploadFilmByUser(){
		String statement = "SELECT title,date,genre,director FROM "+DBManager.getDbName()+".films WHERE user_id = "+this.uniqueID+";";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				ResultSet rs = st.executeQuery(statement);
				){
				while(rs.next()){
					this.uploadedFilms.add(new Film(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"none",0,false,false,true));
				}
			
		}catch(SQLException e){
			System.out.println("Problem with loading upload films for user");
			e.printStackTrace();
		}
	}



	public void addFilmToFavouriteDB(Film film) {
		String statement = "INSERT INTO "+DBManager.getDbName()+".films (title,date,genre,director) VALUES (?,?,?,?);";
		try(
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(statement);
				){
			st.setString(1, film.getTitle());
			st.setString(2, film.getDate());
			st.setString(3, film.getGenre());
			st.setString(4, film.getDirector());
			st.executeUpdate();
		}catch(SQLException e){
			System.out.println("problem with add film first in table films");
			e.printStackTrace();
		}
		
		String secondState = "INSERT INTO "+DBManager.getDbName()+".favourite_films (user_id,film_title,film_date) VALUES (?,?,?);";
		try(PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(secondState);){
			
			st.setInt(1, this.uniqueID);
			st.setString(2, film.getTitle());
			st.setString(3, film.getDate());
			st.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("Problem with add to fav films");
			e.printStackTrace();
		}
	}



	public void removeFromFavouriteDB(Film film) {
		String statement = "DELETE FROM "+DBManager.getDbName()+".favourite_films WHERE user_id = "+this.uniqueID+" AND film_title = \""+film.getTitle()+"\" AND film_date = \""+film.getDate()+"\";";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				
				){
			st.execute(statement);
		}catch(SQLException e){
			System.out.println("Problem with deleteing favourite films");
			e.printStackTrace();
		}
		
	}
	
	public void setCommentINDB(String string, Film film) {
		String statement = "UPDATE "+DBManager.getDbName()+".films SET comment = \""+string+"\" WHERE title = \""+film.getTitle()+"\" AND date = \""+film.getDate()+"\"";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				){
			st.executeUpdate(statement);
		}catch(SQLException e){
			System.out.println("Problem with added comment in DB");
			e.printStackTrace();
		}
		
		
	}
	

	public void addFilmToWatchedDB(Film film) {
		String statement = "INSERT INTO "+DBManager.getDbName()+".films (title,date,genre,director) VALUES (?,?,?,?);";
		try(
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(statement);
				){
			st.setString(1, film.getTitle());
			st.setString(2, film.getDate());
			st.setString(3, film.getGenre());
			st.setString(4, film.getDirector());
			st.executeUpdate();
		}catch(SQLException e){
			System.out.println("problem with add film first in table films");
			e.printStackTrace();
		}
		
		String secondState = "INSERT INTO "+DBManager.getDbName()+".watched_films (user_id,film_title,film_date) VALUES (?,?,?);";
		try(PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(secondState);){
			
			st.setInt(1, this.uniqueID);
			st.setString(2, film.getTitle());
			st.setString(3, film.getDate());
			st.executeUpdate();
			
		}catch(SQLException e){
			System.out.println("Problem with add to fav films");
			e.printStackTrace();
		}
	}



	public void removeFromWatchedDB(Film film) {
		String statement = "DELETE FROM "+DBManager.getDbName()+".watched_films WHERE user_id = "+this.uniqueID+" AND film_title = \""+film.getTitle()+"\" AND film_date = \""+film.getDate()+"\";";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				
				){
			st.execute(statement);
		}catch(SQLException e){
			System.out.println("Problem with deleteing favourite films");
			e.printStackTrace();
		}
	}



	public void addFilmToOwnDB(Film film) {
		String statement = "INSERT INTO "+DBManager.getDbName()+".films (title,date,genre,director,user_id) VALUES (?,?,?,?,?);";
		try(
				PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(statement);
				){
			st.setString(1, film.getTitle());
			st.setString(2, film.getDate());
			st.setString(3, film.getGenre());
			st.setString(4, film.getDirector());
			st.setInt(5, this.uniqueID);
			st.executeUpdate();
		}catch(SQLException e){
			System.out.println("Problem with add own film in DB");
			e.printStackTrace();
		}
	}



	public void deleteFilmINDB(Film film) {
		String statement = "DELETE FROM "+DBManager.getDbName()+".films WHERE (user_id = "+this.uniqueID+" AND title = \""+film.getTitle()+"\")";
		try(
				Statement st = (Statement) DBManager.getInstance().getConnection().createStatement();
				){
			st.executeUpdate(statement);
		}catch(SQLException e){
			System.out.println("problem with delete own film in db");
			e.printStackTrace();
		}
	}
}
