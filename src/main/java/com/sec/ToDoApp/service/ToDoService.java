package com.sec.ToDoApp.service;

import java.util.List;
import java.util.Optional;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;

public interface ToDoService {

	void addToDo(ToDoRequest request);
	Optional<ToDo> findById(long userId, long id);
	List<ToDo> findAll(long user_id);
	void updateStatus(long id);
	void updateToDo(ToDoRequest request);
	void deleteToDo(long id);
}
