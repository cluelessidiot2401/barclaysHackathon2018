package com.diligents.model.barclaysUsers;

import javax.validation.constraints.NotNull;

public class BranchInfo {

	@NotNull
	private int branchId;

	@NotNull
	private String branchName;

	@NotNull
	private String branchAddress;

	@NotNull
	private String branchCode;

	public String getBranchAddress() {
		return branchAddress;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public int getBranchId() {
		return branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
