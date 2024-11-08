package com.sec.ToDoApp.service;

import java.util.List;
import java.util.Optional;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;

public interface ToDoService {

	void addToDo(ToDoRequest request, String username);

	Optional<ToDo> findById(String username, long id);

	List<ToDo> findAll(String username);

	void updateStatus(long id, String username);

	void updateToDo(ToDoRequest request, String username);

	void deleteToDo(long id, String username);

	List<ToDo> findAllTodos();

	void deleteToDoById(long id);
	
	List<ToDo> getPaginatedList(int page, int limit, String username);
}
