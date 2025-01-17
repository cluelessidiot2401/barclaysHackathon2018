package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

public class UserInfo {

	@NotNull
	private int uId;

	@NotNull
	private String name;

	@NotNull
	private String emailId;

	@NotNull
	private String phoneNo;

	@NotNull
	@Range(min = 0, max = 1)
	private int enabled;

	@NotNull
	private String dob;

	@NotNull
	private String panNo;

	@NotNull
	private String gender;

	private String address;

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
