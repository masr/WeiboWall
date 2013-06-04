package cn.edu.nju.software.lidejia.smsreciver;

public class Message {

	private String telephone;
	private String user;
	private String content;

	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(String telephone, String user, String content) {
		super();
		this.telephone = telephone;
		this.user = user;
		this.content = content;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
