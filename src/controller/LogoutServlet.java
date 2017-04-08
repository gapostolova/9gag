package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.DBManager;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("logged", false);
        session.invalidate();
        response.sendRedirect("index.jsp");
	}
	
	@Override
	public void destroy() {
		DBManager.getInstance().closeConnection();
		super.destroy();
	}

}
