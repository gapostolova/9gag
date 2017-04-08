package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UserManager;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		if(!UserDAO.allUsers.containsKey(email)) {
			response.sendRedirect("register.jsp");
		}
		
		if(UserManager.getInstance().validateLogin(email, password)) {
			session.setAttribute("logged", true);
			session.setAttribute("user", UserDAO.getUser(email));
			response.sendRedirect("index.html");
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

}
