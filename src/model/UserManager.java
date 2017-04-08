package model;

import java.util.concurrent.ConcurrentHashMap;

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
	
	public boolean validateLogin(String username, String password) {
		return UserDAO.allUsers.get(username).getPassword().equals(password);	
	}
}
