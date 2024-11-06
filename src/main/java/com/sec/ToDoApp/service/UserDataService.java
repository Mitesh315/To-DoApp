package com.sec.ToDoApp.service;

import java.util.List;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;

public interface UserDataService {
	
	void addUserData(UserDataRequest request);
	void updatePassword(String username, String password);
	boolean loginUserData(String username, String password);
	
	UserData findById(long userId);
	List<UserData> findAll();
	boolean validateUserdata(String username, String password);
	String verify(UserDataRequest req);
	UserData getUser(String username);

}
