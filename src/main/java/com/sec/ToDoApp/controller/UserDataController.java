
package com.sec.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/create")
	public ResponseEntity<String> addUserData(@RequestBody UserDataRequest request) {
		try {
			userDataService.addUserData(request);
			return ResponseEntity.ok("User Created");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@PutMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody UserDataRequest req) {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			userDataService.updatePassword(username, req.getPassword());
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
	
	@PostMapping("/userdata")
	public UserData getUser() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		return userDataService.getUser(username);
	}
	
//	@PreAuthorize("hasRole('ADMIN')")
//	@GetMapping("/userdata")
//	public ResponseEntity<List<UserData>> findAll() {
//		try {
//			return ResponseEntity.ok(userDataService.findAll());
//		}
//		catch (Exception e) {
//			return ResponseEntity.badRequest().body(null);
//		}
//	}
	
//	@PostMapping("/authenticate")
//	public String authenticate(Authentication auth) {
//			if(userDataService.validateUserdata(username, password)) {
//				return jwtUtil.generateToken(auth.getName());
//			}
//			return "Invalid Credentials";
//	}
	
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDataRequest req) {
		try {
			return ResponseEntity.ok(userDataService.verify(req));
		}
		catch(Exception e) {
			return ResponseEntity.badRequest().body("Unauthorized user found");
		}
	}
}
