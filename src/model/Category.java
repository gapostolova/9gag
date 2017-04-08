package model;

public class Category {
	
	private long categoryID;
	private String categoryName;
	
	public Category(long categoryID, String categoryName) {
		this.categoryID = categoryID;
		if(categoryName != null && !categoryName.isEmpty()){
			this.categoryName = categoryName;
		}
	}
	
	

}
