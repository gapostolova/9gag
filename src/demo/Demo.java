package demo;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.Category;
import model.Comment;
import model.Gag;
import model.User;
import model.dao.GagDAO;
import model.dao.UserDAO;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
	
	Set<Gag> gags = GagDAO.getInstance().getAllGags();
	System.out.println(gags);
	System.out.println("======================================================\n");
	
//	Gag gag = new Gag("smeshnichko", "Dano da stane", 22, true, true, "jpg");
//	gag.setUpvotes(600);
//	gag.addCategory(new Category(1, "funny"));
//	gag.addCategory(new Category(2, "savage"));
//	GagDAO.getInstance().addGag(gag);
//	
	
	Set<Gag> gags2 = GagDAO.getInstance().getAllGags();
	System.out.println(gags2);
	System.out.println("======================================================\n");
	
	Map<String, User> users = UserDAO.getInstance().getAllUsers();
	System.out.println(users);
	System.out.println("======================================================\n");
	System.out.println("====================  	HOT		===============\n");
	
	
	Set<Gag> hot = GagDAO.getInstance().hotGags();
	System.out.println(hot);
	
System.out.println("======================	TRENDING 	=======================\n");
	
	Set<Gag> TREN = GagDAO.getInstance().trendingGags();
	System.out.println(TREN);
	
	
System.out.println("======================	FRESH 	=======================\n");
	
	Set<Gag> fresh = GagDAO.getInstance().freshGags();
	System.out.println(fresh);
	
	System.out.println("======================	ALL 	=======================\n");
	
	Set<Gag> gags3 = GagDAO.getInstance().getAllGags();
	System.out.println(gags3);
	
System.out.println("======================	savage 	=======================\n");
	
	Set<Gag> gags4 = GagDAO.getInstance().categoryGags("savage");
	System.out.println(gags4);
System.out.println("======================	FUNNY 	=======================\n");
	
	 gags4 = GagDAO.getInstance().categoryGags("funny");
	System.out.println(gags4);
	
	}

}
