package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

public class UserAccount {
	
	@NotNull
	private int uId;
	
	@NotNull
	private String accNo;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	
}
