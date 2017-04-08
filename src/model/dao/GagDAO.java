package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;


import model.Gag;

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
	
	public synchronized Set<Gag> getAllGags() throws SQLException {
		if(allGags.isEmpty() || allGags == null) {
			Statement statement = null;
			ResultSet res = null;
			
			statement = conn.createStatement();
			res = statement.executeQuery("SELECT gag_id, content, nsfw, title, user_id, public, type FROM gags;");
			while(res.next()) {
				allGags.add(new Gag(
						res.getString("content"), 
						res.getString("title"), 
						res.getLong("user_id"), 
						res.getLong("gag_id"), 
						res.getBoolean("nsfw"), 
						res.getBoolean("isPublic"), 
						res.getString("type")));
			}
		}
		return Collections.unmodifiableSet(allGags);
	
	}
	
	
}
