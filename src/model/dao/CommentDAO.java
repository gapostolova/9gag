package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import model.Comment;
import model.Gag;

public class CommentDAO {

	public static TreeSet<Comment> allComments;
	private static CommentDAO instance;
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
	
	public synchronized void addComments(TreeSet<Comment> comments){
		allComments.addAll(comments);
	}
	
	//this should be boolean
	public void addComment(Comment comment) {
		
		try {
		String sql = "INSERT INTO `9gag`.`comments` (`time`, `description`, `mothership_id`, `points`, `user_id`, `gag_id`) VALUES (?,?,?,?,?,?)";
		PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
			pst.setTimestamp(1, Timestamp.valueOf(comment.getDate()));
		
		pst.setString(2, comment.getContent());
		pst.setLong(3, comment.getMotherCommentId());
		pst.setInt(4, 0);
		pst.setLong(5, comment.getUserId());
		pst.setLong(6, comment.getGagId());
		pst.executeUpdate();
		
		//add commentId
	    ResultSet res = pst.getGeneratedKeys();
		res.next();
		long commentId = res.getLong(1);
		comment.setCommentId(commentId);
		
		System.out.println("comment v db");
		System.out.println(comment);
		Gag gag = GagDAO.getInstance().getGagById(comment.getGagId());
		gag.addComment(comment);
		
		} catch (SQLException e) {
			System.out.println("couldn't add comment in CommentDAO: " + e.getMessage());
		}
		
		
	}
	
	
	//working comment deletion
	public synchronized void deleteComment(Comment c) throws SQLException {
		try {
			//get all comments that have this comment id as their mother comment
			
			String sql = "DELETE FROM `9gag`.`comments` WHERE `comment_id`=? OR mothership_id=?;";
			PreparedStatement pst = conn.prepareStatement(sql);

			pst.setLong(1, c.getCommentId());
			pst.setLong(2, c.getCommentId());
			
			pst.executeUpdate();
			
			//delete comment from gag
			GagDAO.getInstance().deleteComment(c);
			
			} catch (SQLException e) {
				System.out.println("Could not delete comment in deleteComment in CommentDAO: " + e.getMessage());
				throw new SQLException(e.getMessage());
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
	
	//delete all comments form a gag (when deleting a gag)
	public synchronized void deleteGagComments(Gag gag) throws SQLException {
		
		try {
			String sql = "DELETE FROM `9gag`.`comments` WHERE `gag_id`=? ;";
			PreparedStatement pst = conn.prepareStatement(sql);
	
			pst.setLong(1, gag.getGagID());
			
			pst.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("Could not delete comments in for a gag in CommentDAO: " + e.getMessage());
			throw new SQLException(e.getMessage());
		}
	}
}
