package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

public class Transaction {
	
	@NotNull
	private int uId;
	
	@NotNull
	private String accNo;
	
	@NotNull
	private String toAccNo;
	
	@NotNull
	private String date;
	
	@NotNull
	private String time;

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

	public String getToAccNo() {
		return toAccNo;
	}

	public void setToAccNo(String toAccNo) {
		this.toAccNo = toAccNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
