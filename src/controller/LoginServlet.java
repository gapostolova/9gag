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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		
		try {
			if(!UserDAO.getInstance().getAllUsers().containsKey(email)) {
				response.sendRedirect("register.jsp");
				return;
			}
		} catch (SQLException e) {
			//error page
		}
		
		try {
			String url = "";
			if(UserManager.getInstance().validateLogin(email, password)) {
				session.setAttribute("logged", true);
				session.setAttribute("user", UserDAO.getInstance().getUser(email));
				url = "index.html";
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
