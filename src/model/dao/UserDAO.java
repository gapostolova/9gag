package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;


import model.Comment;
import model.Gag;

import model.User;


public class UserDAO {

	//						Email, User
	private static ConcurrentHashMap<String, User> allUsers = new ConcurrentHashMap<>();
	private boolean dataHasChanged = false;
	private static UserDAO instance;
	
	private UserDAO() {}
	
	public static synchronized UserDAO getInstance() {
		if(instance == null){
			instance = new UserDAO();
		}
		return instance;
	}
	
	public void setDataHasChanged(boolean dataHasChanged) {
		this.dataHasChanged = dataHasChanged;
	}
	
	
	public synchronized Map<String , User> getAllUsers() throws SQLException{
		
		if(allUsers.isEmpty() || dataHasChanged == true){
			
			String sql = "SELECT u.user_id, u.username, u.password, u.email, u.nsfw, u.profile_pic, u.gender, u.birthday, u.description, u.admin, u.is_verified, u.verification_key from users u;";
			PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while(res.next()){
				int isVerified = res.getInt("is_verified");
			
				User user = new User(res.getString("username"), res.getString("email"), res.getString("password"), res.getInt("user_id"), res.getBoolean("nsfw"), res.getString("profile_pic"), res.getString("gender"), res.getDate("birthday").toLocalDate(), res.getString("description"), res.getBoolean("admin"));
				System.out.println(user);
				//add gags/videos and comments
				
				
				allUsers.put(user.getEmail(), user);
			}
		}
		dataHasChanged = false;
		return Collections.unmodifiableMap(allUsers);
	}
	
	
	//get all gags of user with id : userId
	/*private synchronized TreeSet<Gag> usersGags(long userId){
		
		TreeSet<Gag> gags = new TreeSet<Gag>();
		String sql = "SELECT gag_id, content, nsfw, title, points, public, type, user_id FROM gags WHERE user_id = " + userId + ";";
		PreparedStatement st = DBManager.getInstance().getConnection().prepareStatement(sql);
		ResultSet res = st.executeQuery();
		
		while(res.next()){
			
		}
		
	}*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//get all users
//	public Set<User> getAllUsers() {
//		Set<User> allUsers = new HashSet<>();
//		
//		Statement statement = null;
//		ResultSet res = null;
//		
//		try {
//			statement = DBManager.getInstance().getConnection().createStatement();
//			res = statement.executeQuery("SELECT user_id, username, password, email, nsfw, profile_pic, gender, birthday, country, description, admin FROM users;");
//			while(res.next()) {
//				User user = new User(res.getString("username"), 
//						res.getString("email"), 
//						res.getString("password"), 
//						res.getInt("user_id"), 
//						res.getBoolean("nsfw"), 
//						res.getString("profile_pic"), 
//						res.getString("gender"), 
//						res.getDate("birthday").toLocalDate(), 
//						res.getString("country"),
//						res.getString("description"),
//						res.getBoolean("admin"));
//				allUsers.add(user);
//				//add gags and videos
//			}
//		} catch (SQLException e) {
//			System.out.println("Couldn't retrieve users from DB");
//		} finally {
//			try {
//				statement.close();
//				res.close();
//			} catch (SQLException e) {
//				System.out.println("Couldn't close statement or resultset.");
//			}
//			
//		}
//		
//		System.out.println("Got all users from DB");
//		
//		return Collections.unmodifiableSet(allUsers);
//	}
	
	//add user --- whole process should be a transaction
	
	public void addUser(User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
				"INSERT INTO users (username, password, email, nsfw, profile_pic, gender, birthday, country, description, admin) VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			pst.setString(1, u.getUsername());
			pst.setString(2, u.getPassword());
			pst.setString(3, u.getEmail());
			pst.setBoolean(4, u.isViewNsfwContent());
			pst.setString(5, u.getProfilePic());
			pst.setString(6, u.getGender());
			pst.setDate(7, java.sql.Date.valueOf(u.getDateOfBirth()));
			pst.setString(9, u.getDescription());
			pst.setBoolean(10, u.isAdmin());
			pst.executeUpdate();
			
			//add id
		    ResultSet res = pst.getGeneratedKeys();
			res.next();
			long id = res.getLong(1);
			u.setUserId(id);
			res.close();
		    System.out.println("User inserted successfuly into DB");
		    
		} catch (SQLException e) {
			System.out.println("Couldn't insert user into DB.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement");
			}
		}
	}
	
		//delete user
	
	public synchronized void deleteUser(User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"DELETE FROM TABLE users WHERE user_id = ?");
			pst.setLong(1, u.getUserId());
			pst.executeUpdate();
			System.out.println("User successfuly deleted.");
		} catch (SQLException e) {
			System.out.println("Couldn't delete user.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
	//all of the methods above should be validated in the UserManager
	
		//change password
	
	public synchronized void changePass(String pass, User u) {
		
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"UPDATE users SET password = ? WHERE user_id = ?;");
			pst.setString(1, pass);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user password.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update password.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
		
	}
	
		//change profile pic
	
	public synchronized void changePic(String pic, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"UPDATE users SET profile_pic = ? WHERE user_id = ?;");
			pst.setString(1, pic);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user profile pic.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update pic.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
		//change nsfw status
	
	public synchronized void changeNSFW(boolean nsfw, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"UPDATE users SET nsfw = ? WHERE user_id = ?;");
			pst.setBoolean(1, nsfw);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user nsfw status.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update nsfw status.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
	
		//change description
	
	public synchronized void changeDescription(String description, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"UPDATE users SET description = ? WHERE user_id = ?;");
			pst.setString(1, description);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated user description.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update user description.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
		//change admin status
	
	public synchronized void changeAdmin(boolean admin, User u) {
		PreparedStatement pst = null;
		
		try {
			pst = DBManager.getInstance().getConnection().prepareStatement(
					"UPDATE users SET admin = ? WHERE user_id = ?;");
			pst.setBoolean(1, admin);
			pst.setLong(2, u.getUserId());
			pst.executeUpdate();
			System.out.println("Updated admin status.");	
		} catch (SQLException e) {
			System.out.println("Couldn't update admin status.");
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				System.out.println("Couldn't close prepared statement.");
			}
		}
	}
		
}
