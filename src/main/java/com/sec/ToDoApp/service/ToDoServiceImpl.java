package com.sec.ToDoApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;
import com.sec.ToDoApp.repository.ToDoRepository;
import com.sec.ToDoApp.repository.UserDataRepository;

@Service
public class ToDoServiceImpl implements ToDoService {

	@Autowired
	private ToDoRepository toDoRepository;

	@Autowired
	private UserDataRepository userDataRepository;

	@Override
	public void addToDo(ToDoRequest request, String username) {
		long userId = userDataRepository.findIdByUsername(username);
		toDoRepository.addToDo(request, userId);
	}

	@Override
	public Optional<ToDo> findById(String username, long id) {
		long userId = userDataRepository.findIdByUsername(username);
		return toDoRepository.findById(userId, id);
	}

	@Override
	public void updateStatus(long id, String username) {
		long userId = userDataRepository.findIdByUsername(username);
		toDoRepository.updateStatus(id, userId);
	}

	@Override
	public List<ToDo> findAll(String username) {
		long userId = userDataRepository.findIdByUsername(username);
		return toDoRepository.findAll(userId);
	}

	@Override
	public void updateToDo(ToDoRequest request, String username) {
		long userId = userDataRepository.findIdByUsername(username);
		toDoRepository.updateToDo(request, userId);
	}

	@Override
	public void deleteToDo(long id, String username) {
		long userId = userDataRepository.findIdByUsername(username);
		toDoRepository.deleteToDo(id, userId);

	}

	@Override
	public List<ToDo> findAllTodos() {
		return toDoRepository.findAllTodos();
	}

	@Override
	public void deleteToDoById(long id) {
		toDoRepository.deleteToDoById(id);
	}

	@Override
	public List<ToDo> getPaginatedList(int page, int limit, String username) {
		long userId = userDataRepository.findIdByUsername(username);
		return toDoRepository.getPaginatedList(page, limit, userId);
	}
}
