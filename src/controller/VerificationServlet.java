package controller;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.UserDAO;


/**
 * Servlet implementation class VerifyServlet
 */
@WebServlet("/verification")
public class VerificationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VerificationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String key = req.getParameter("verificationKey");
		try {
			
			if(!UserDAO.getInstance().exists(email)){
				resp.getWriter().write("Account does not exist!");

			}
			else if(!UserDAO.getInstance().isVerified(email)){
				if(UserDAO.getInstance().verify(email,key))
					resp.getWriter().write("Thank you for verifying your account!");
				else resp.getWriter().write("Please try again.");

			}	
			else resp.getWriter().write("Your account is verified!");
		} catch (SQLException e) {
			 resp.getWriter().write("Ooops something get wrong!");	
			 e.printStackTrace();	 
		}

	}
		
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
