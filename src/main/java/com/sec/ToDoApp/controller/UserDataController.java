package com.sec.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.jwt_util.JwtUtil;
import com.sec.ToDoApp.model.UserData;
import com.sec.ToDoApp.service.UserDataService;

@RestController
@RequestMapping("/todoApp")
public class UserDataController {

	@Autowired
	private UserDataService userDataService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@PostMapping("/userdata")
	public ResponseEntity<String> addUserData(@RequestBody UserDataRequest request) {
		try {
			userDataService.addUserData(request);
			return ResponseEntity.ok("User Created");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping("/userdata/{userId}")
	public ResponseEntity<String> updatePassword(@PathVariable long userId, @RequestParam String password) {
		try {
			userDataService.updatePassword(userId, password);
			return ResponseEntity.ok("Password updated");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to update password");
		}
	}
	
	@GetMapping("/userdata/{userId}")
	public ResponseEntity<UserData> findById(@PathVariable long userId) {
		try {
			return ResponseEntity.ok(userDataService.findById(userId));
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@GetMapping("/userdata")
	public ResponseEntity<List<UserData>> findAll() {
		try {
			return ResponseEntity.ok(userDataService.findAll());
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
	}
	
	@PostMapping("/authenticate")
	public String authenticate(@RequestParam String username, @RequestParam String password) {
			if(userDataService.validateUserdata(username, password)) {
				return jwtUtil.generateToken(username);
			}
			return "Invalid Credentials";
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestHeader("Authorization") String token) {
		if(token != null) {
			if(jwtUtil.validateToken(token))
				return ResponseEntity.ok("User logged in successfully");
		}
		throw new RuntimeException("Unauthorized user found");
	}
	
}
