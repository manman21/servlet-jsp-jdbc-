package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		NewModel news = new NewModel();
		try {
			news.setId(resultSet.getLong("id"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setTitle(resultSet.getString("title"));
			news.setContent(resultSet.getString("content")); 
			news.setShortDescription(resultSet.getString("shortdescription"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setCreatedDate(resultSet.getTimestamp("createddate"));
			news.setCreatedBy(resultSet.getString("createdby"));
			if(resultSet.getTimestamp("modifieddate") != null) {
				news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			}
			if(resultSet.getString("modifiedby") != null) {
				news.setModifiedBy(resultSet.getString("modifiedby"));
			}
			return news;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
