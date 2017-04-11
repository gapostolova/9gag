package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import model.Comment;
import model.Gag;

public class CommentDAO {

	public static TreeSet<Comment> allComments;
	public static CommentDAO instance;
	private static boolean dataHasChanged;
	private	Connection conn = DBManager.getInstance().getConnection();
	
	private CommentDAO() {
		allComments = new TreeSet<>();
	}
	
	public static synchronized CommentDAO getInstance() {
		if(instance == null)
			instance = new CommentDAO();
		return instance;
	}
	
	public synchronized void addComment(Comment c) throws SQLException {
			try {
				Gag gagTemp = null;
				for(Gag gag : UserDAO.getInstance().getAllUsers().get(c.getUserEmail()).getGags()) {
					if(gag.getGagID() == c.getGagId()) {
						gagTemp = gag;
						break;
					}
				}
				String sql = "INSERT INTO `9gag`.`comments` (`time`, `description`, `mothership_id`, `points`, `user_id`, `gag_id`) VALUES (?,?,?,?,?,?)";
				PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				//pst.setTime(1, c.getDate());
				pst.setString(2, c.getContent());
				pst.setLong(3, c.getMotherCommentId());
				pst.setInt(4, 0);
				pst.setLong(5, c.getUserId());
				pst.setLong(6, c.getGagId());
				pst.executeUpdate();
				
				//add commentId
			    ResultSet res = pst.getGeneratedKeys();
				res.next();
				long commentId = res.getLong(1);
				c.setCommentId(commentId);
				System.out.println("comment v db");
				System.out.println(c);
				
				//add comment to main collection
				GagDAO.getInstance().addCommentToGag(c);
				
			} catch (SQLException e) {
				System.out.println("Error adding gag!");
				throw new SQLException("Error upvoting!");
			}
		
	}
	
	public synchronized void deleteComment(Comment c) throws SQLException {
		try {
			
			String sql = "DELETE FROM `9gag`.`comments` WHERE `comment_id`='?';";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setLong(1, c.getCommentId());
			pst.executeUpdate();
			
			//delete comment from gag
			GagDAO.getInstance().deleteComment(c);
			
			} catch (SQLException e) {
				System.out.println("Error upvoting!");
				throw new SQLException("Error upvoting!");
			}
		
	}
	
	public synchronized void rateComment(Comment c, int point) throws SQLException {
		try {
			
		if(point > 0)
			c.Upvote();
		else
			c.Downvote();
		
		String sql = "UPDATE `9gag`.`comments` SET `points`='?' WHERE `comment_id`='?';";
		PreparedStatement pst = conn.prepareStatement(sql);
		
		pst.setInt(1, c.getUpvotes());
		pst.setLong(2, c.getCommentId());
		pst.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Error upvoting!");
			throw new SQLException("Error upvoting!");
		}
		

	}
}
