package model;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class Comment {
	
	private  int userId;
	private int gagId;
	private int commentId;
	private LocalDateTime date;
	private String content;
	private int motherCommentId;
	private int upvotes;
	
	//?? private TreeSet<Comment> replies;
	
	public Comment(int userId, int gagId, int commentId, LocalDateTime date, String content, int motherCommentId) {
		super();
		this.userId = userId;
		this.gagId = gagId;
		commentId = commentId;
		this.date = date;
		if(content != null && !content.isEmpty()){
			this.content = content;
		}
		this.motherCommentId = motherCommentId;
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	
	public void Downvote(){
		this.upvotes--;
	}
	

}
