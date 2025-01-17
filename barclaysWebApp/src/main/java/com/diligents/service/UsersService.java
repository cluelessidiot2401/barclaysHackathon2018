package com.diligents.service;

import java.util.List;

import javax.validation.Valid;

import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.Transaction;
import com.diligents.model.barclaysUsers.UserInfo;

public interface UsersService {
	
	UserInfo addUser(@Valid UserInfo userInfo);

	@Valid Login register(@Valid Login login);

	Login validateLogin(@Valid Login login);
	
	int changePass(String uname,String cpass,String npass);
	
	int changeMob(String uname,String mcpass,String nmob);

	Login addUser(@Valid Login login);

	Integer getBalance(String userNameFromFE);

	Integer transferFunds(String toAccountNo, int amount, String userName, String myPass);

	String getAccountNumberbyUserId(String userName);

	List<Transaction> getTransactionHistoryByUName(String userName);


}