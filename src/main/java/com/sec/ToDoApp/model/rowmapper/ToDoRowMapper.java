package com.sec.ToDoApp.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sec.ToDoApp.model.ToDo;

public class ToDoRowMapper implements RowMapper<ToDo>{

	@Override
	public ToDo mapRow(ResultSet rs, int rowNum) throws SQLException {
		ToDo todo = new ToDo();
		todo.setId(rs.getLong("id"));
		todo.setTitle(rs.getString("title"));
		todo.setDescription(rs.getString("description"));
		todo.setStatus(rs.getString("status"));
		todo.setUserId(rs.getLong("user_id"));
		
		return todo;
	}

}
