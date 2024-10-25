package com.sec.ToDoApp.model.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.sec.ToDoApp.model.UserData;

public class UserDataRowMapper implements RowMapper<UserData>{

	@Override
	public UserData mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserData userData = new UserData();
		userData.setUserId(rs.getLong("user_id"));
		userData.setUsername(rs.getString("username"));
		userData.setPassword(rs.getString("password"));
		userData.setRole(rs.getString("role"));
		
		return userData;
	}
	

}
