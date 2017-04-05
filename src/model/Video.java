package model;

import java.util.TreeSet;

public class Video implements Comparable<Video>{
	
	private String title;
	private String videoURL;
	private int videoId;
	private int userId;
	private int upvotes;
	private boolean isYoutube; 
	
	private TreeSet<Comment> comments;
	
	public Video(String title, String video, boolean isYoutube, int videoId, int userId) {
		super();
		if(title != null && !title.isEmpty()){
			this.title = title;
		}
		if(videoURL != null && !videoURL.isEmpty()){
			this.videoURL = video;
		}
		this.isYoutube = isYoutube;
		this.videoId = videoId;
		this.userId = userId;
		this.comments = new TreeSet<>();
	}
	
	public void Upvote(){
		this.upvotes++;
	}
	
	public void Downvote(){
		this.upvotes--;
	}
	

	@Override
	public int compareTo(Video o) {
		return o.videoId - this.videoId;
	}
	
	
	
	

}
