package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.UserDAO;
import model.UserManager;

@WebServlet("/logIn")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("pass");
		HttpSession session = request.getSession();
		
		try {
			if(!UserDAO.getInstance().getAllUsers().containsKey(email)) {
				session.setAttribute("notAMember", "It seems like you don't have an account yet! ");
				response.sendRedirect("register.jsp");
				return;
			}
		} catch (SQLException e) {
			//error page
			System.out.println("Could not getAllUsers in LoginServlet: " + e.getMessage());
		}
		
		try {
			String url = "";
			if(UserManager.getInstance().validateLogin(email, password)) {
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDAO.getInstance().getUser(email));
				url = "index.jsp";
			}
			else {
				url = "login.jsp";
			}
			response.sendRedirect(url);
			return;
		} catch (SQLException e) {
			//error page
		}
		
		
	}

}
