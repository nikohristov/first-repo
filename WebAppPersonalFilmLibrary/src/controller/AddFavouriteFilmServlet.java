package controller;

import java.io.IOException;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;
import model.User;


@WebServlet("/AddFavouriteFilmServlet")
public class AddFavouriteFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getSession().getAttribute("loggedUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		
		String movieTitle = request.getParameter("setFav");
		String movieDate = request.getParameter("filmDate");
		System.out.println(movieTitle);
		TreeSet<Film> films = (TreeSet<Film>) request.getSession().getAttribute("films");
		User user = (User) request.getSession().getAttribute("loggedUser");
		for(Film film : films){
			if(film.getTitle().equals(movieTitle) && film.getDate().equals(movieDate)){
				if(film.isFavourite == false){
					user.addFilmToFavouriteDB(film);
					film.setIsFavourite(true);
					request.getSession().setAttribute("films", films);
					request.getRequestDispatcher("main.jsp").forward(request, response);
					return;
				}else{
					user.removeFromFavouriteDB(film);
					film.setIsFavourite(false);
					request.getRequestDispatcher("main.jsp").forward(request, response);
					return;
				}
			}
		}
	}

}
