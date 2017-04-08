package model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Gag implements Comparable<Gag> {
	
	private String gag;
	private String title;
	private long userId;
	private long gagID;
	private boolean nsfw;
	private ArrayList<Category> category;
	private int upvotes;
	private boolean isPublic;
	private TreeSet<Comment> comments;
	private String type;
	
	

	public Gag(String gag, String title, long userId, long gagID, boolean nsfw, boolean isPublic, String type) {
		if(gag != null && !gag.isEmpty()){
			this.gag = gag;
		}
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		this.userId = userId;
		this.gagID = gagID;
		this.nsfw = nsfw;
		
		this.comments = new TreeSet<Comment>();
		this.isPublic = isPublic;
		
		if(type != null && !type.isEmpty()){
			this.type = type;
		}
		this.category = new ArrayList<>();
	}
	
	public long getUserId() {
		return userId;
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	public void setUpvotes(int upvotes) {
		this.upvotes = upvotes;
	}
	
	public void Downvote(){
		this.upvotes--;
	}
	
	public void addComment(Comment comment){
		this.comments.add(comment);
	}
	
	public void setComments(TreeSet<Comment> comments) {
		this.comments = comments;
	}
	
	public long getGagID() {
		return gagID;
	}
	
	public void setCategory(ArrayList<Category> category) {
		this.category = category;
	}
	
	
	
	@Override
	public int compareTo(Gag g) {
		return (int) (g.gagID-this.gagID);
	}

	@Override
	public String toString() {
		return "Gag [gag=" + gag + ", title=" + title + ", userId=" + userId + ", gagID=" + gagID + ", nsfw=" + nsfw
				+ ", category=" + category + ", upvotes=" + upvotes + ", isPublic=" + isPublic + ", comments="
				+ comments + ", type=" + type + "]";
	}
	
	
}
