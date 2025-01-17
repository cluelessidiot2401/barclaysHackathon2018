package com.diligents.repository;

import java.util.List;

import javax.validation.Valid;

import com.diligents.model.barclaysUsers.Login;
import com.diligents.model.barclaysUsers.LoginDetails;
import com.diligents.model.barclaysUsers.Transaction;
import com.diligents.model.barclaysUsers.UserInfo;

public interface UsersRepository {

	UserInfo addUser(@Valid UserInfo userInfo);

	List<UserInfo> getUsersByEmailId(String emailId);

	int getUserIdByEmailId(String emailId);

	Login addUser(@Valid Login login);

	UserInfo getUser(Integer id);

	LoginDetails validateForUser(int userId);

	LoginDetails getUserById(int userId);

	int getUserIdByEmailIdFromLoginDetails(String emailId);

	List<LoginDetails> getUserByPasswordAndEmail(String emailId, String getuPassword);

	Integer getBalanceForUId(int userIdByEmailId);

	Integer getUIdFromAccNo(String toAccountNo);

	String getEmailIdbyUId(Integer uIdForReceiver);

	String getAccNoFromUId(int userIdByEmailId);

	int updateBalanceForAccNo(String toAccountNo, int newBalanceForReceiver);

	Transaction addTransaction(Transaction transaction);

	List<Transaction> getTransactionHistory(String userName);


}