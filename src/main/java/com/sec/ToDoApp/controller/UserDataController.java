
package com.sec.ToDoApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
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

	
	
	// Create New User

	@PostMapping("/create-user")
	public ResponseEntity<String> addUserData(@RequestBody UserDataRequest request) {
		try {
			
			if(request.getUsername() == null || request.getUsername().trim().isEmpty()) {
				throw new IllegalArgumentException("Username cannot be null or Empty");
			}
			
			if(request.getPassword() == null || request.getPassword().trim().isEmpty()) {
				throw new IllegalArgumentException("Passowrd cannot be null or Empty");
			}
			
			userDataService.addUserData(request);
			return ResponseEntity.ok("User Created");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Internal Server Error");
		}
	}

	// Update Password

	@PutMapping("/update-password")
	public ResponseEntity<String> updatePassword(@RequestBody UserDataRequest req) {
		try {
			
			if(req.getPassword() == null || req.getPassword().trim().isEmpty()) {
				throw new IllegalArgumentException("Passowrd cannot be null or Empty");
			}
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			userDataService.updatePassword(username, req.getPassword());
			return ResponseEntity.ok("Password updated");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to update password");
		}
	}


	// Get User data by giving token

	@PostMapping("/userdata")
	public UserData getUser() {
		
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			return userDataService.getUser(username);
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Info not found");
		}
		
		
	}

	
	// Login User and return JWT token
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserDataRequest req) {
		try {
			
			if(req.getUsername() == null || req.getUsername().trim().isEmpty()) {
				throw new IllegalArgumentException("Username cannot be null or Empty");
			}
			
			if(req.getPassword() == null || req.getPassword().trim().isEmpty()) {
				throw new IllegalArgumentException("Passowrd cannot be null or Empty");
			}
			
			return ResponseEntity.ok(userDataService.verify(req));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unauthorized user found");
		}
	}
	
	
	// Delete user by giving token
	
	@DeleteMapping("/delete-user")
	public ResponseEntity<String> deleteUserData() {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			userDataService.deleteUserData(username);
			return ResponseEntity.ok("User Deleted Successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error occured while deleting user");
		}
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

	
//	ADMIN API's
	
	
	// Get user data by id
	
	@GetMapping("/userdata/{userId}")
	public ResponseEntity<UserData> findById(@PathVariable long userId) {
		try {
			return ResponseEntity.ok(userDataService.findById(userId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("User data not found");			
		}
	}
	
	
	// Create Admin
	
	
	@PostMapping("/create-admin")
	public ResponseEntity<String> addAdminData(@RequestBody UserDataRequest request) {
		try {
			
			if(request.getUsername() == null || request.getUsername().trim().isEmpty()) {
				throw new IllegalArgumentException("Username cannot be null or Empty");
			}
			
			if(request.getPassword() == null || request.getPassword().trim().isEmpty()) {
				throw new IllegalArgumentException("Passowrd cannot be null or Empty");
			}
			
			userDataService.addAdminData(request);
			return ResponseEntity.ok("Admin account Created");
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error occured while creating account");
		}
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get-all-user")
	public ResponseEntity<List<UserData>> getAllUser() {
		try {
			return ResponseEntity.ok(userDataService.getAllUser());
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("List not found");
		}
	}
	

}
