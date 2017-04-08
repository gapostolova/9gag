package model;

import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

import model.dao.UserDAO;

public class UserManager {

	public static UserManager instance;
	private ConcurrentHashMap<String, User> temp = new ConcurrentHashMap<>();
	//temp collection for testing
	
	private UserManager() {}
	
	public static synchronized UserManager getInstance() {
		if(instance == null)
			instance = new UserManager();
		return instance;
	}
	
	public boolean validateLogin(String username, String password) throws SQLException {
		return UserDAO.getInstance().getAllUsers().get(username).getPassword().equals(password);	
	}
}
