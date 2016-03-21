package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import db.DBUserDAO;
import model.Film;
import model.User;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBUserDAO dao = new DBUserDAO();
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		if(dao.checkIfUserExists(name)){
			for(User user : dao.getAllUsers()){
				if(user.getPassword().equals(password)){
					HttpClient client = HttpClientBuilder.create().build();
					HttpUriRequest zaqvka = new HttpGet("http://www36.imperiaonline.org/movies.php");
					HttpResponse otgovor = client.execute(zaqvka);
					String data = EntityUtils.toString(otgovor.getEntity());
					
					JsonArray filmsFromHttp = (JsonArray) new JsonParser().parse(data);
					HashSet<Film> films = user.getAllFilmsForUser();
					
					for(int i=0; i<filmsFromHttp.size(); i++){
						JsonObject obj = filmsFromHttp.get(i).getAsJsonObject();
						String title = obj.get("Title").getAsString();
						String date = (obj.get("Year").getAsString());
						String genre = obj.get("Genre").getAsString();
						String director = obj.get("Director").getAsString();
						Film film = new Film(title,date,genre,director);
						if(!films.contains(film)){
							films.add(film);
						}
					}
					
					TreeSet<Film> sortedFilms = new TreeSet<Film>(films);
					request.getSession().setAttribute("loggedUser", user);
					request.getSession().setAttribute("films", sortedFilms);
					request.getRequestDispatcher("main.jsp").forward(request, response);
					return;
				}
			}
		}else{
			request.setAttribute("errorMessage", "Wrong username or password");
			request.getRequestDispatcher("index.jsp").forward(request, response);;
			return;
		}	
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String orderBy = req.getParameter("orderBy");
		TreeSet<Film> films = null;
		System.out.println(orderBy);
		switch(orderBy){
		case "ASC":
			films = new TreeSet<Film>();
			films.addAll((TreeSet<Film>) req.getSession().getAttribute("films"));
			req.getSession().setAttribute("films", films);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		case "DESC":
		 films = new TreeSet<Film>(Collections.reverseOrder()); 
			
			films.addAll((TreeSet<Film>) req.getSession().getAttribute("films"));
			 
			req.getSession().setAttribute("films", films);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
			return;
		}
	}

}
