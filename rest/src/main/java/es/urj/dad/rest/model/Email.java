package es.urj.dad.rest.model;

public class Email {

	private String userName;
	
	private String emailAddress;
	
	private String content;
	
	protected Email() {
	}
	
	public Email(String emailAddress, String userName, String content) {
		this.emailAddress = emailAddress;
		this.userName = userName;
		this.content = content;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
