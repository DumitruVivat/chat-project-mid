package lib;

import java.io.Serializable;

public class User implements Serializable{

	private String nickname;
	// private String password;

	public User(String nickname) {
		this.setNickname(nickname);;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Override
	public String toString() {
		return "User > " + nickname;
	}
	
	
}
