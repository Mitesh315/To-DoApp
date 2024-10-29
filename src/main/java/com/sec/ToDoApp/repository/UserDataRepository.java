package com.sec.ToDoApp.repository;

import java.util.List;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;

public interface UserDataRepository {
	
//	API for User
	void addUserData(UserDataRequest request);
	void updatePassword(long userId, String password);
	UserData findByUsername(String username);
	
	
//	API for Admin
	UserData findById(long userId);
	List<UserData> findAll();
	

}
