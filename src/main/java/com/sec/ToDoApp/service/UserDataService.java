package com.sec.ToDoApp.service;

import java.util.List;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;

public interface UserDataService {
	
	void addUserData(UserDataRequest request);
	void updatePassword(String username, String password);
	boolean loginUserData(String username, String password);
	void deleteUserData(String username); 
	
	void addAdminData(UserDataRequest request);
	UserData findById(long userId);
	boolean validateUserdata(String username, String password);
	String verify(UserDataRequest req);
	UserData getUser(String username);
	List<UserData> getAllUser();

}
