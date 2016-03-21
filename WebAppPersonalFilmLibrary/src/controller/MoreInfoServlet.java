package controller;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Film;
import model.User;

@WebServlet("/MoreInfoServlet")
public class MoreInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loggedUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		String comment = request.getParameter("comment");
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		User user = (User) request.getSession().getAttribute("loggedUser");
		TreeSet<Film> films = (TreeSet<Film>) request.getSession().getAttribute("films");
		for(Film film : films){
			if(film.getTitle().equals(title) && film.getDate().equals(date)){
				StringBuilder desc = new StringBuilder(film.getComment());
				desc.append(" "+comment+"\n");
				user.setCommentINDB(desc.toString(), film);
				film.setComment(desc.toString());
				request.setAttribute("FilmForInfo", film);
				request.getRequestDispatcher("showInfo.jsp").forward(request, response);
				return;
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loggedUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		String title = request.getParameter("title");
		String date = request.getParameter("date");
		
		TreeSet<Film> films = (TreeSet<Film>) request.getSession().getAttribute("films");
		for(Film film : films){
			if(film.getTitle().equals(title) && film.getDate().equals(date)){
				HttpClient client = HttpClientBuilder.create().build();
				String titleRequest = title.replace(" ", "-");
				HttpUriRequest zaqvka = new HttpGet("http://www.omdbapi.com/?t="+titleRequest+"&y="+date+"&plot=short&r=json");
				HttpResponse otgovor = client.execute(zaqvka);
				String data = EntityUtils.toString(otgovor.getEntity());
				
				JsonObject obj = (JsonObject) new JsonParser().parse(data);
				film.setRuntime(obj.get("Runtime").getAsString());
				film.setRating(obj.get("imdbRating").getAsDouble());
				film.setPoster(obj.get("Poster").getAsString());
				
				
				request.setAttribute("FilmForInfo", film);
				request.getRequestDispatcher("showInfo.jsp").forward(request, response);
				return;
			}
		}
		
	}

}
