package com.sec.ToDoApp.service;

import java.util.List;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;

public interface UserDataService {
	
	void addUserData(UserDataRequest request);
	void updatePassword(long userId, String password);
	
	UserData findById(long userId);
	List<UserData> findAll();

}