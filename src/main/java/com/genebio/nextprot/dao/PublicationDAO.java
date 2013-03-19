package com.genebio.nextprot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Component;

import com.genebio.nextprot.domain.Publication;

@Component("publicationDAO")
public class PublicationDAO {

	@Autowired
	private DataSource datasource;

	private String sqlHeader = "SELECT resource_id, title, abstract_text, publication_date from nextprot.publications ";
	private static class PublicationRowMapper implements ParameterizedRowMapper<Publication> {

		public Publication mapRow(ResultSet resultSet, int row) throws SQLException {

			// Need to use a mapper, but it is not so bad if we don't want to use reflection since the database may use different names
			Publication publication = new Publication();
			publication.setId(resultSet.getLong("resource_id"));
			publication.setTitle(resultSet.getString("title"));
			publication.setAbstractText(resultSet.getString("abstract_text"));
			publication.setPublicationDate(resultSet.getDate("publication_date"));
			return publication;
		}
	};

	
	public Publication getPublicationById(long id) {
	
		String sql = sqlHeader + "where resource_id = :resource_id";

		// Spring advantages: No need to open / close connection or to worry about result set...
		// We can use named parameters which are less error prone
		SqlParameterSource namedParameters = new MapSqlParameterSource("resource_id", id);
		return new NamedParameterJdbcTemplate(datasource).queryForObject(sql, namedParameters, new PublicationRowMapper());
	}

	
	
	public List<Publication> getPublicationByTitle(String title) {
	
/*		String sql = sqlHeader + "where lower(title) like lower(:1)";
		
		return new JdbcTemplate(datasource).query(sql, new PublicationRowMapper(), title);
*/
	
		String sql = sqlHeader + "where lower(title) like lower(:title)";
		
		// Spring advantages: No need to open / close connection or to worry about result set...
		// We can use named parameters which are less error prone
		SqlParameterSource namedParameters = new MapSqlParameterSource("title", title);
		return new NamedParameterJdbcTemplate(datasource).query(sql, namedParameters, new PublicationRowMapper());

	
	}
}
