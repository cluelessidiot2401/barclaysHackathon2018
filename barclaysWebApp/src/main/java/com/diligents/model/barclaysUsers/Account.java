package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

public class Account {

	@NotNull
	private String accNo;

	@NotNull
	private int branchId;

	private String accType;

	@NotNull
	private int balance;

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getAccType() {
		return accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	
}
