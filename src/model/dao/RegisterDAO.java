package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import emailSender.SendEmail;
//import emailSender.SendEmail;
import model.User;

public class RegisterDAO {
	
	private	Connection conn = DBManager.getInstance().getConnection();
	private static RegisterDAO instance;
	
	public synchronized static RegisterDAO getInstance(){
		if(instance == null){
			instance = new RegisterDAO();
		}
		return instance;
	}
	
public boolean register(String username, String email, String password){
		
		if (conn != null) {
			
			String sql = "SELECT email FROM users WHERE email=?";
			PreparedStatement s;
			try {
				s = conn.prepareStatement(sql);
				s.setString(1, email);
				ResultSet r = s.executeQuery();
				
				//if there isn't such email
				if (!r.isBeforeFirst()) {
					//verification key
					String uuid = UUID.randomUUID().toString();
					//resp.getWriter().write("account made");
					 sql = "INSERT INTO `9gag`.`users` ( `username`, `password`, `email`, `nsfw`, `profile_pic`, `gender`, `birthday`, `description`, `admin`, `is_verified`, `verification_key`) "
							+ "VALUES ( ?,?,?,?,?,?,?,?,?,?,?);";
					User user = new User(username, email, password);
					s = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
					s.setString(1, user.getUsername());
					s.setString(2, user.getPassword());
					s.setString(3, user.getEmail());
					s.setBoolean(4, user.isViewNsfwContent());
					s.setString(5, user.getProfilePic());
					s.setString(6, user.getGender());
					s.setDate(7, java.sql.Date.valueOf(user.getDateOfBirth()));
					s.setString(8, user.getDescription());
					s.setBoolean(9, user.isAdmin());
					s.setInt(10, 0);
					s.setString(11, uuid);
					s.executeUpdate();
					
					//add id
				    ResultSet res = s.getGeneratedKeys();
					res.next();
					long id = res.getLong(1);
					user.setUserId(id);
					
					
					
					
				    System.out.println("User inserted successfuly into DB");
				    
				    UserDAO.getInstance().addUser(user);
					SendEmail.sendVerificationMail(email, username, uuid);
						
					return true;
				}
			} catch (SQLException e) {
				System.out.println("SQL not responsive: " + e.getMessage());
				return false;
			}
		}
		return false;
	}

	
	
	
}
