package com.sec.ToDoApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sec.ToDoApp.dto.UserDataRequest;
import com.sec.ToDoApp.model.UserData;
import com.sec.ToDoApp.model.rowmapper.UserDataRowMapper;

@Repository
public class UserDataRepositoryImpl implements UserDataRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	
//	Users
	
	@Override
	public void addUserData(UserDataRequest request) {
		String sql = "INSERT INTO user_data (username, password, role) VALUES (?, ?, 'USER')";
		jdbcTemplate.update(sql, request.getUsername(), request.getPassword());
	}

	@Override
	public void updatePassword(String username, String password) {
		String sql = "UPDATE user_data SET password = ? where username = ?";
		jdbcTemplate.update(sql, password, username);	
	}
	
	@Override
	public UserData findByUsername(String username) {
		String sql = "SELECT * FROM user_data WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new UserDataRowMapper(), username);
	}
	
	
//	Admin
	
	
	@Override
	public UserData findById(long userId) {
		String sql = "SELECT * FROM user_data WHERE user_id = ?";
		return jdbcTemplate.queryForObject(sql, new UserDataRowMapper(), userId);
	}


	@Override
	public long findIdByUsername(String username) {
		String sql = "SELECT user_id FROM user_data WHERE username = :username";
		
		MapSqlParameterSource params = new MapSqlParameterSource();
		
		params.addValue("username", username);
		
		return namedParameterJdbcTemplate.queryForObject(sql, params, Long.class);
	}

	@Transactional
	@Override
	public void deleteUserData(long userId) {
		
		String sql1 = "DELETE FROM todo WHERE user_id = ?";
		jdbcTemplate.update(sql1, userId);
		
		String sql2 = "DELETE FROM user_data WHERE user_id = ? AND role = 'USER' ";
		jdbcTemplate.update(sql2, userId);
		
	}

	@Override
	public void addAdminData(UserDataRequest request) {
		String sql = "INSERT INTO user_data (username, password, role) VALUES (?, ?, 'ADMIN')";
		jdbcTemplate.update(sql, request.getUsername(), request.getPassword());
	}

	@Override
	public List<UserData> getAllUser() {
		String sql = "SELECT * FROM user_data WHERE role = 'USER'";
		return jdbcTemplate.query(sql, new UserDataRowMapper());
	}

		
	
}
