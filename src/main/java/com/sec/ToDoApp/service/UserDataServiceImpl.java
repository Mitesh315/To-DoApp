package com.sec.ToDoApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.jwt_util.JwtUtil;
import com.sec.ToDoApp.model.UserData;
import com.sec.ToDoApp.repository.UserDataRepository;

@Service
public class UserDataServiceImpl implements UserDataService{

	@Autowired
	private UserDataRepository userDataRepository;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Autowired
	AuthenticationManager authManager;
	
	@Override
	public void addUserData(UserDataRequest request) {
		request.setPassword(encoder.encode(request.getPassword()));
		userDataRepository.addUserData(request);
	}

	@Override
	public void updatePassword(String username, String password) {
		password = encoder.encode(password);
		userDataRepository.updatePassword(username, password);
		
	}

	@Override
	public UserData findById(long userId) {
		return userDataRepository.findById(userId);
	}

	@Override
	public boolean validateUserdata(String username, String password) {
		UserData user = userDataRepository.findByUsername(username);
		return user != null && user.getPassword().equals(encoder.encode(password));
	}

	@Override
	public boolean loginUserData(String username, String password) {
		UserData userData = userDataRepository.findByUsername(username);
		return userData != null && userData.getPassword().equals(password);
	}

	@Override
	public String verify(UserDataRequest req) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(),req.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtUtil.generateToken(req.getUsername());
		}
		
		return "Failed to Login";
	}

	@Override
	public UserData getUser(String username) {
		return userDataRepository.findByUsername(username);
	}

	@Override
	public void deleteUserData(String username) {
		long userId = userDataRepository.findIdByUsername(username);
		userDataRepository.deleteUserData(userId);
		
	}

	@Override
	public void addAdminData(UserDataRequest request) {
		request.setPassword(encoder.encode(request.getPassword()));
		userDataRepository.addAdminData(request);
		
	}

	@Override
	public List<UserData> getAllUser() {
		return userDataRepository.getAllUser();
	}
	
	
}
