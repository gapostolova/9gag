package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.GagDAO;

/**
 * Servlet implementation class GetContentServlet
 */
@WebServlet("/getcontent")
public class GetContentServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("gags", GagDAO.getInstance().getAllGags());
		
		response.sendRedirect("index.jsp");
	}

}
