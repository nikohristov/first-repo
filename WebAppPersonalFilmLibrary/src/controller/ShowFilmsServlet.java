package controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Film;

@WebServlet("/ShowFilmsServlet")
public class ShowFilmsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("loggedUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		TreeSet<Film> current = new TreeSet<>();

		current.addAll((TreeSet<Film>) request.getSession().getAttribute("films"));
		
		for(Iterator it = current.iterator(); it.hasNext();){
			Film film = (Film) it.next();
			if(film.getisFavourite() == false){
				it.remove();
			}
		}
		
		request.getSession().setAttribute("favFilms", current);
		request.getRequestDispatcher("showFavourite.jsp").forward(request, response);
		return;
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		if(request.getSession().getAttribute("loggedUser") == null){
			response.sendRedirect("index.jsp");
			return;
		}
		TreeSet<Film> core = new TreeSet<>();

		core.addAll((TreeSet<Film>) request.getSession().getAttribute("films"));
		
		for(Iterator it = core.iterator(); it.hasNext();){
			Film film = (Film) it.next();
			if(film.isWatched() == false){
				it.remove();
			}
		}
		
		request.getSession().setAttribute("favFilms", core);
		request.getRequestDispatcher("showFavourite.jsp").forward(request, response);
		return;
		
	}

}
