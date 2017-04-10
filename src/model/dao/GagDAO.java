package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;


import model.Gag;
import model.User;

public class GagDAO {

	public static ConcurrentSkipListSet<Gag> allGags;
	public static GagDAO instance;
	private	Connection conn = DBManager.getInstance().getConnection();
	
	private GagDAO () {
		allGags = new ConcurrentSkipListSet<>();
	}
	
	public static synchronized GagDAO getInstance() {
		if(instance == null)
			instance = new GagDAO();
		return instance;
	}
	

	public synchronized Set<Gag> getAllGags() {
		
		try {
			Map<String, User> users = UserDAO.getInstance().getAllUsers();
			for(User user : users.values()){
				Set<Gag> gags =  user.getGags();
				allGags.addAll(gags);
			}
			
		} catch (SQLException e) {
			System.out.println("Could not get users from UserDAO in GagDAO: "+ e.getMessage());
		}
		
		return Collections.unmodifiableSet(allGags);
	}
	
	public void addGag(Gag gag) throws SQLException{
		String sql = "INSERT INTO `9gag`.`gags` (`content`, `nsfw`, `title`, `points`, `user_id`, `public`, `type`) VALUES (?,?,?,?,?,?,?);";
		
		PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, gag.getGag());
		pst.setBoolean(2, gag.isNsfw());
		pst.setString(3, gag.getTitle());
		pst.setLong(4, gag.getUserId());
		pst.setBoolean(5, gag.isPublic());
		pst.setString(6, gag.getType());
		pst.executeUpdate();
		
		//add gagId
	    ResultSet res = pst.getGeneratedKeys();
		res.next();
		long gagId = res.getLong(1);
		gag.setGagID(gagId);
		UserDAO.getInstance().addGagToUser(gag);
		
	}
	
	
}
