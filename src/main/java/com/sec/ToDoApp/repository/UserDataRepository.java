package com.sec.ToDoApp.repository;

import java.util.List;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;

public interface UserDataRepository {
	
//	API for User
	void addUserData(UserDataRequest request);
	void updatePassword(String username, String password);
	UserData findByUsername(String username);
	void deleteUserData(String username);
	
	
//	API for Admin
	UserData findById(long userId);
	long findIdByUsername(String username);
	void addAdminData(UserDataRequest request);
	List<UserData> getAllUser();
	

}
