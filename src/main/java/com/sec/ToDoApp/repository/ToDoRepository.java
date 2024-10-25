package com.sec.ToDoApp.repository;

import java.util.List;
import java.util.Optional;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;

public interface ToDoRepository {
	
	void addToDo(ToDoRequest request);
	List<ToDo> findAll(long user_id);
	Optional<ToDo> findById(long userId, long id);
	void updateToDo(ToDoRequest request);
	void updateStatus(long id);
	void deleteToDo(long id);

}
