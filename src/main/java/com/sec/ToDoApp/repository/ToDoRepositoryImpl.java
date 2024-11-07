package com.sec.ToDoApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;
import com.sec.ToDoApp.model.rowmapper.ToDoRowMapper;

@Repository
public class ToDoRepositoryImpl implements ToDoRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addToDo(ToDoRequest request, long id) {
		String sql = "INSERT INTO todo (title, description, user_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, request.getTitle(), request.getDescription(), id);
	}

	@Override
	public List<ToDo> findAll(long userId) {
		String sql = "SELECT * FROM todo WHERE user_id = ?";
		return jdbcTemplate.query(sql, new ToDoRowMapper(), userId);
	}

	@Override
	public Optional<ToDo> findById(long userId, long id) {
		String sql = "SELECT * FROM todo WHERE user_id = ? AND id = ?";
		return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new ToDoRowMapper(), userId, id));
	}
	
	@Override
	public void updateToDo(ToDoRequest request, long userId) {
		String sql = "UPDATE todo SET title = ?, description = ? WHERE user_id = ? AND id = ?";
		jdbcTemplate.update(sql, request.getTitle(), request.getDescription(), userId, request.getId());
	}

	@Override
	public void deleteToDo(long id, long userId) {
		String sql = "DELETE FROM todo WHERE user_id = ? AND id = ?";
		jdbcTemplate.update(sql, userId, id);
	}

	@Override
	public void updateStatus(long id, long userId) {
		String sql = "UPDATE todo SET status = 'COMPLETED' WHERE user_id = ? AND id = ?";
		jdbcTemplate.update(sql, userId, id);
	}

	@Override
	public List<ToDo> findAllTodos() {
		String sql = "SELECT * FROM todo";
		return jdbcTemplate.query(sql, new ToDoRowMapper());
	}
	
	
}
