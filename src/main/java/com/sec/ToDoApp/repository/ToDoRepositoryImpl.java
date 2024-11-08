package com.sec.ToDoApp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import com.sec.ToDoApp.dto.ToDoRequest;
import com.sec.ToDoApp.model.ToDo;
import com.sec.ToDoApp.model.rowmapper.ToDoRowMapper;

@Repository
public class ToDoRepositoryImpl implements ToDoRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private ToDoRowMapper toDoRowMapper;

	@Override
	public void addToDo(ToDoRequest request, long id) {
		String sql = "INSERT INTO todo (title, description, user_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, request.getTitle(), request.getDescription(), id);
	}

	@Override
	public List<ToDo> findAll(long userId) {
		String sql = "SELECT * FROM todo WHERE user_id = ? ORDER BY title";
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

	@Override
	public void deleteToDoById(long id) {
		String sql = "DELETE FROM todo WHERE id=?";
		jdbcTemplate.update(sql,id);
	}
	
	@Override
	public List<ToDo> getPaginatedList(int page, int limit, long userId) {
		int offset = page * limit;
		String sql = "SELECT * FROM todo where user_id = ? ORDER BY title ASC LIMIT ? OFFSET ?";
		
		return jdbcTemplate.query(
		        new PreparedStatementCreator() {
		            @Override
		            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		                PreparedStatement ps = connection.prepareStatement(sql);
		                ps.setLong(1, userId);
		                ps.setInt(2, limit);     // Set LIMIT
		                ps.setInt(3, offset);   // Set OFFSET
		                return ps;
		            }
		        },
		        toDoRowMapper
		    );
	}
	
	
	
}
