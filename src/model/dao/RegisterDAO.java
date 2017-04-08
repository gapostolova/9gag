package model.dao;

import java.sql.Connection;

public class RegisterDAO {
	
	private	Connection conn = DBManager.getInstance().getConnection();
	private static RegisterDAO instance;
	
	public synchronized static RegisterDAO getInstance(){
		if(instance == null){
			instance = new RegisterDAO();
		}
		return instance;
	}

	
	
	
}
