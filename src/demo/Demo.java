package demo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.Comment;
import model.Gag;
import model.User;
import model.dao.GagDAO;
import model.dao.UserDAO;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
	
	Set<Gag> gags = GagDAO.getInstance().getAllGags();
	System.out.println(gags);
	
	}

}
