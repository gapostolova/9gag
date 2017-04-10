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

import model.Category;
import model.Gag;
import model.User;

public class GagDAO {

	private static final int MIN_TRENDING_GAG_UPVOTES = 500;
	private static final int MIN_HOT_GAG_UPVOTES = 2000;
	public static TreeSet<Gag> allGags;
	public static GagDAO instance;
	private	Connection conn = DBManager.getInstance().getConnection();
	
	private GagDAO () {
		allGags = new TreeSet<>();
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
		pst.setInt(4, gag.getUpvotes());
		pst.setLong(5, gag.getUserId());
		pst.setBoolean(6, gag.isPublic());
		pst.setString(7, gag.getType());
		pst.executeUpdate();
		
		//add gagId
	    ResultSet res = pst.getGeneratedKeys();
		res.next();
		long gagId = res.getLong(1);
		gag.setGagID(gagId);
		System.out.println("GAG V GAG DAO");
		System.out.println(gag);
		//add gags' categories ;(
		for(Category cat : gag.getCategory()){
			
			sql = "INSERT INTO `9gag`.`gags_in_categories` (`gags_gag_id`, `categories_category_id`) VALUES (?, ?);";
			pst = conn.prepareStatement(sql);
			pst.setLong(1, gag.getGagID());
			pst.setLong(2, cat.getCategoryID());
			pst.executeUpdate();
		}
		
		UserDAO.getInstance().addGagToUser(gag);
		
	}
	
	
	
	
	public Set<Gag> hotGags(){
		TreeSet<Gag> hot = new TreeSet<>();
		for(Gag gag : allGags){
			if(gag.getUpvotes() >= MIN_HOT_GAG_UPVOTES){
				hot.add(gag);
			}
		}
		return Collections.unmodifiableSet(hot);
	}
	
	public Set<Gag> trendingGags(){
		TreeSet<Gag> trending = new TreeSet<>();
		for(Gag gag : allGags){
			if(gag.getUpvotes() < MIN_HOT_GAG_UPVOTES && gag.getUpvotes() >= MIN_TRENDING_GAG_UPVOTES){
				trending.add(gag);
			}
		}
		return Collections.unmodifiableSet(trending);
	}
	
	
	public Set<Gag> freshGags(){
		TreeSet<Gag> fresh = new TreeSet<>();
		for(Gag gag : allGags){
			if(gag.getUpvotes() < MIN_TRENDING_GAG_UPVOTES){
				fresh.add(gag);
			}
		}
		return Collections.unmodifiableSet(fresh);
	}
	
	public Set<Gag> categoryGags(String category){
		TreeSet<Gag> categories = new TreeSet<>();
		for(Gag gag : allGags){
			if(gag.hasCategory(category)){
				categories.add(gag);
			}
		}
		return Collections.unmodifiableSet(categories);
	}
	
	
	
	
	
}
