package com.sec.ToDoApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;
import com.sec.ToDoApp.service.ToDoService;

@RestController
@RequestMapping("/todoApp")
public class ToDoController {

	@Autowired
	private ToDoService toDoService;
	
	@PostMapping("/todo")
	public ResponseEntity<String> addToDo(@RequestBody ToDoRequest request) {
		try {
			toDoService.addToDo(request);
			return ResponseEntity.ok("ToDo added successfully");
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@GetMapping("/todo/{userId}/{id}")
	public Optional<ToDo> findById(@PathVariable long userId, @PathVariable long id) {
		try {
			return toDoService.findById(userId, id);
		}
		catch (Exception e) {
			return null;
		}
	}
	
	@GetMapping("/todo/{userId}")
	public List<ToDo> findAll(@PathVariable long userId) {
		try {
			return toDoService.findAll(userId);
		}
		catch(Exception e) {
			return null;
		}
	}
	
	@PutMapping("/todo")
	public ResponseEntity<String> updateToDo(@RequestBody ToDoRequest request) {
		try {
			toDoService.updateToDo(request);
			return ResponseEntity.ok("ToDo Updated");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to Update ToDo");
		}
	}
	
	@PutMapping("/todo/{id}")
	public ResponseEntity<String> updateStatus(@PathVariable long id) {
		try {
			toDoService.updateStatus(id);
			return ResponseEntity.ok("Status Updated");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("To-Do not Found");
		}
	}
	
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<String> deleteToDo(@PathVariable long id) {
		try {
			toDoService.deleteToDo(id);
			return ResponseEntity.ok("To-Do deleted");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body("Failed to delete");
		}
	}
}
