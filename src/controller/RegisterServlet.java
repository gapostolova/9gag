package controller;
//TODO GabiA
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emailSender.SendEmail;
import model.dao.DBManager;
import model.dao.RegisterDAO;
import model.dao.UserDAO;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().write("");
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String username = req.getParameter("username");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
	
		String passConfirm = req.getParameter("passConfirm");
		
		
		if(!password.equals(passConfirm)){
			resp.getWriter().write("passwords did not match");
			return;
		}
		
		if(RegisterDAO.getInstance().register(username, email, password)){
			UserDAO.getInstance().setDataHasChanged(true);
			resp.getWriter().write(" kk, you should verify your account");
		}
		else{
			resp.getWriter().write("could not add user");
		}
		
	
		
	}

}
