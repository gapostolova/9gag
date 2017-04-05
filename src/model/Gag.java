package model;

import java.util.TreeSet;

public class Gag implements Comparable<Gag> {
	
	private String gag;
	private String title;
	private int userId;
	private int gagID;
	private boolean nsfw;
	private String category;
	private int upvotes;
	private boolean isPublic;
	private TreeSet<Comment> comments;
	//?? private boolean isVideo;
	
	

	public Gag(String gag, String title, int userId, int gagID, boolean nsfw, String category, boolean isPublic) {
		if(gag != null && !gag.isEmpty()){
			this.gag = gag;
		}
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		this.userId = userId;
		this.gagID = gagID;
		this.nsfw = nsfw;
		if(category != null && !category.isEmpty()){
			this.category = category;
		}
		this.comments = new TreeSet<Comment>();
		this.isPublic = isPublic;
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	
	public void Downvote(){
		this.upvotes--;
	}
	
	
	
	@Override
	public int compareTo(Gag g) {
		// TODO Auto-generated method stub
		return g.gagID-this.gagID;
	}
}
