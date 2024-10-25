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
	public void addToDo(ToDoRequest request) {
		String sql = "INSERT INTO todo (title, description, user_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, request.getTitle(), request.getDescription(), request.getUserId());
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
	public void updateToDo(ToDoRequest request) {
		String sql = "UPDATE todo SET title = ?, description = ? WHERE id = ?";
		jdbcTemplate.update(sql, request.getTitle(), request.getDescription(), request.getId());
	}

	@Override
	public void deleteToDo(long id) {
		String sql = "DELETE FROM todo WHERE id = ?";
		jdbcTemplate.update(sql, id);
		
	}

	@Override
	public void updateStatus(long id) {
		String sql = "UPDATE todo SET status = 'COMPLETED' WHERE id = ?";
		jdbcTemplate.update(sql, id);
	}
	
	
}
