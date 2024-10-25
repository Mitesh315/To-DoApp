package com.sec.ToDoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;
import com.sec.ToDoApp.repository.ToDoRepository;

@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoRepository toDoRepository;

	@Override
	public void addToDo(ToDoRequest request) {
		toDoRepository.addToDo(request);
	}

	@Override
	public Optional<ToDo> findById(long userId, long id) {
		return toDoRepository.findById(userId, id);
	}

	@Override
	public void updateStatus(long id) {
		toDoRepository.updateStatus(id);
	}

	@Override
	public List<ToDo> findAll(long user_id) {
		return toDoRepository.findAll(user_id);
	}

	@Override
	public void updateToDo(ToDoRequest request) {
		toDoRepository.updateToDo(request);
	}

	@Override
	public void deleteToDo(long id) {
		toDoRepository.deleteToDo(id);
		
	}
}
