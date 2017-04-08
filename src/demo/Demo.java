package demo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

import model.Comment;
import model.Gag;
import model.User;
import model.dao.UserDAO;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
	Map<String, User> all = UserDAO.getInstance().getAllUsers();
	String defaultBirthday = "1916-01-01";
	LocalDate b = LocalDate.parse(defaultBirthday);
	System.out.println(b);
	System.out.println(all);
	}

}
