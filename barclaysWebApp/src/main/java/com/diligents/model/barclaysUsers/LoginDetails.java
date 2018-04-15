package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

public class LoginDetails {
	
	@NotNull
	private int uId;
	
	@NotNull
	public String uName;

	@NotNull
	private String uPassword;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}
	
}
