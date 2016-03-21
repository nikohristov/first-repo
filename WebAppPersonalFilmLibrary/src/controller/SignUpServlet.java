package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DBUserDAO;


@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		DBUserDAO dao = new DBUserDAO();
		
		if(dao.checkIfUserExists(username)){
			request.setAttribute("errorMessage", "yes");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}else{
			dao.registerUser(username, password);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		
	}

}
