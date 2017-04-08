package model;

import java.time.LocalDateTime;
import java.util.TreeSet;

public class Comment implements Comparable<Comment> {
	
	private  long userId;
	private long gagId;
	private long commentId;
	private LocalDateTime date;
	private String content;
	private long motherCommentId;
	private int upvotes;
	
	//?? private TreeSet<Comment> replies;
	
	public Comment(long userId, long gagId, long commentId, LocalDateTime date, String content, long motherCommentId) {
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

	@Override
	public int compareTo(Comment o) {
		return (int) (this.gagId - o.gagId);
	}

	@Override
	public String toString() {
		return "Comment [userId=" + userId + ", gagId=" + gagId + ", commentId=" + commentId + ", date=" + date
				+ ", content=" + content + ", motherCommentId=" + motherCommentId + ", upvotes=" + upvotes + "]";
	}
	
	
	

}
