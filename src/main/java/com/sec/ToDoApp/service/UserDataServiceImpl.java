package com.sec.ToDoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;
import com.sec.ToDoApp.repository.UserDataRepository;

@Service
public class UserDataServiceImpl implements UserDataService{

	@Autowired
	private UserDataRepository userDataRepository;

	@Override
	public void addUserData(UserDataRequest request) {
		userDataRepository.addUserData(request);
	}

	@Override
	public void updatePassword(long userId, String password) {
		userDataRepository.updatePassword(userId, password);
		
	}

	@Override
	public UserData findById(long userId) {
		return userDataRepository.findById(userId);
	}

	@Override
	public List<UserData> findAll() {
		return userDataRepository.findAll();
	}

	@Override
	public boolean validateUserdata(String username, String password) {
		return false;
	}

	@Override
	public boolean loginUserData(String username, String password) {
		UserData userData = userDataRepository.findByUsername(username);
		return userData != null && userData.getPassword().equals(password);
	}
	
	
}
