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
import model.User;

@WebServlet("/AddOwnFilmServlet")
public class AddOwnFilmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getSession().getAttribute("loggedUser") == null){
			resp.sendRedirect("index.jsp");
			return;
		}
		String name = req.getParameter("forDel");
		User user = (User) req.getSession().getAttribute("loggedUser");
		TreeSet<Film> films = (TreeSet<Film>) req.getSession().getAttribute("films");
		for(Iterator it = films.iterator(); it.hasNext();){
			Film film = (Film) it.next();
			if(film.getTitle().equals(name)){
				user.deleteFilmINDB(film);
				it.remove();
				req.getRequestDispatcher("main.jsp").forward(req, resp);
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
		String genre = request.getParameter("genre");
		String director = request.getParameter("director");
		User user = (User) request.getSession().getAttribute("loggedUser");
		
		Film film = new Film(title,date,genre,director);
		film.setUpload(true);
		user.addFilmToOwnDB(film);
		TreeSet<Film> current = (TreeSet<Film>) request.getSession().getAttribute("films");
		current.add(film);
		request.getRequestDispatcher("main.jsp").forward(request, response);
		return;
	}

}
