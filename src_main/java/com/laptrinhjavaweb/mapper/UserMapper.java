package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		UserModel users = new UserModel();
		try {
			users.setId(resultSet.getLong("id"));
			users.setFullName(resultSet.getString("fullname"));
			users.setUserName(resultSet.getString("username"));
			users.setPassword(resultSet.getString("password"));
			users.setStatus(resultSet.getInt("status")); 
			users.setRoleId(resultSet.getLong("roleid"));
			users.setCreatedDate(resultSet.getTimestamp("createddate"));
			users.setCreatedBy(resultSet.getString("createdby"));
			try {
				RoleModel role = new RoleModel();
				role.setCode(resultSet.getString("code"));
				role.setName(resultSet.getString("name"));
				users.setRole(role);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			
			if(resultSet.getTimestamp("modifieddate") != null) {
				users.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				users.setModifiedBy(resultSet.getString("modifiedby"));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
