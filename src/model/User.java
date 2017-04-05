package model;

import java.time.LocalDate;
import java.util.TreeSet;

public class User {
	
	private String username;
	private String email;
	private String password;
	private long userId;
	private boolean viewNsfwContent;
	private String profilePic;
	private final String gender;
	private LocalDate dateOfBirth;
	private String country;
	private String description;
	private boolean admin;
	private TreeSet<Gag> gags;
	private TreeSet<Video> videos;
	
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static final int MAX_YEAR_OF_BIRTH = 2003;
	private static final int MIN_YEAR_OF_BIRTH = 1917;
	
	public User(String username, String email, String password, int userId, boolean nsfw, String profilePic,
			String gender, LocalDate dateOfBirth, String country, String description, boolean admin) {

		setUsername(username);
		setEmail(email);
		setPassword(password);
		setUserId(userId);
		setViewNsfwContent(nsfw);
		setProfilePic(profilePic);
		setDateOfBirth(dateOfBirth);
		setCountry(country);
		setDescription(description);
		setAdmin(admin);
		
		if(gender != null && !gender.isEmpty()){
			this.gender = gender;
		}
		else{
			this.gender = "Male";
		}
		
		this.gags = new TreeSet<Gag>();
		this.videos = new TreeSet<>();
		
	}
	
	
	
	
	public void setUsername(String username) {
		if(username != null && !username.isEmpty()){
			this.username = username;
		}
	}




	public void setEmail(String email) {
		if(email != null && !email.isEmpty() && email.matches(EMAIL_PATTERN)){
			this.email = email;
		}
	}




	public void setPassword(String password) {
		this.password = password;
	}




	public void setViewNsfwContent(boolean viewNsfwContent) {
		this.viewNsfwContent = viewNsfwContent;
	}




	public void setProfilePic(String profilePic) {
		if(profilePic != null && !profilePic.isEmpty()){
			this.profilePic = profilePic;
		}
	}




	public void setDateOfBirth(LocalDate dateOfBirth) {
		if(dateOfBirth.getYear() >= MIN_YEAR_OF_BIRTH && dateOfBirth.getYear() <= MAX_YEAR_OF_BIRTH){
			this.dateOfBirth = dateOfBirth;
		}
	}


	
	public void setUserId(long userId) {
		this.userId = userId;
	}


	public void setCountry(String country) {
		if(country != null && !country.isEmpty()){
			this.country = country;
		}
	}




	public void setGags(TreeSet<Gag> gags) {
		this.gags = gags;
	}




	public void setDescription(String description) {
		if(description!= null && !description.isEmpty())
			this.description = description;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public long getUserId() {
		return userId;
	}

	public boolean isViewNsfwContent() {
		return viewNsfwContent;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public String getGender() {
		return gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getCountry() {
		return country;
	}

	public String getDescription() {
		return description;
	}

	public boolean isAdmin() {
		return admin;
	}

}