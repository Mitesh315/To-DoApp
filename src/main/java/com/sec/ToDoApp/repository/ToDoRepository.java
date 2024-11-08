package com.sec.ToDoApp.repository;

import java.util.List;
import java.util.Optional;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;

public interface ToDoRepository {

	void addToDo(ToDoRequest request, long id);

	List<ToDo> findAll(long userId);

	Optional<ToDo> findById(long userId, long id);

	void updateToDo(ToDoRequest request, long userId);

	void updateStatus(long id, long userId);

	void deleteToDo(long id, long userId);

	List<ToDo> findAllTodos();
	
	void deleteToDoById(long id);
	
	List<ToDo> getPaginatedList(int page, int limit, long userId);

}
