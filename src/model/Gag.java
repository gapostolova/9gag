package model;

import java.util.ArrayList;
import java.util.TreeSet;

public class Gag implements Comparable<Gag> {
	
	private static final int DEFAULT_GAG_ID = -1;
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
	
	
	//constructor for when a gag is added
	public Gag(String gag, String title, long userId, boolean nsfw, boolean isPublic, String type) {
		if(gag != null && !gag.isEmpty()){
			this.gag = gag;
		}
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		this.userId = userId;
		this.nsfw = nsfw;
		this.comments = new TreeSet<Comment>();
		this.isPublic = isPublic;
		
		if(type != null && !type.isEmpty()){
			this.type = type;
		}
		this.upvotes = 0;
		this.category = new ArrayList<>();
		this.gagID = DEFAULT_GAG_ID;
	}
	

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
	
	public int getUpvotes() {
		return upvotes;
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
	
	
	public void addCategory(Category c){
		this.category.add(c);
	}
	
	public boolean hasCategory(String categor){
		for(Category cat : category){
			if(cat.getCategoryName().equals(categor.toUpperCase())){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Category> getCategory() {
		return category;
	}
	
	public long getGagID() {
		return gagID;
	}
	
	public String getGag() {
		return gag;
	}
	
	public boolean isNsfw() {
		return nsfw;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getType() {
		return type;
	}
	
	public void setGagID(long gagID) {
		this.gagID = gagID;
	}
	
	public boolean isPublic() {
		return isPublic;
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
		return "	Gag [gag=" + gag + ", title=" + title + ", userId=" + userId + ", gagID=" + gagID + ", nsfw=" + nsfw
				+ ", category=" + category + ", upvotes=" + upvotes + ", isPublic=" + isPublic + ", comments="
				+ comments + ", type=" + type + "]\n";
	}
	
	
}
