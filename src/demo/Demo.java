package demo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import model.User;
import model.dao.UserDAO;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
	Map<String, User> all = UserDAO.getInstance().getAllUsers();
	
	System.out.println(all);
	}

}
