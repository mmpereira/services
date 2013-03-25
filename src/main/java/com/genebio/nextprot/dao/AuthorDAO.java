package com.genebio.nextprot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.genebio.nextprot.domain.Author;

@Component
public class AuthorDAO {

	@Autowired
	private DataSource datasource;

	private String sqlHeader = "select pubauthor_id, last_name, fore_name from nextprot.pubauthors ";

	private static class AuthorRowMapper implements ParameterizedRowMapper<Author> {

		public Author mapRow(ResultSet resultSet, int row) throws SQLException {

			// Need to use a mapper, but it is not so bad if we don't want to use reflection since the database may use different names
			Author author = new Author();
			author.setId(resultSet.getLong("pubauthor_id"));
			author.setLastName(resultSet.getString("last_name"));
			author.setForeName(resultSet.getString("fore_name"));
			return author;
		}
	};

	public List<Author> getAuthorByPublicationId(long publicationId) {

		String sql = sqlHeader + "where publication_id = :publication_id";

		SqlParameterSource namedParameters = new MapSqlParameterSource("publication_id", publicationId);
		return new NamedParameterJdbcTemplate(datasource).query(sql, namedParameters, new AuthorRowMapper());
	}

}
