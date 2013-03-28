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

import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.domain.PublicationImpl;

@Component
public class PublicationDAO {

	@Autowired
	private DataSource datasource;

	private String sqlHeader = "SELECT pubs.resource_id, pubs.title, pubs.abstract_text, pubs.publication_date from nextprot.publications pubs ";
	private static class PublicationRowMapper implements ParameterizedRowMapper<Publication> {

		public Publication mapRow(ResultSet resultSet, int row) throws SQLException {

			// Need to use a mapper, but it is not so bad if we don't want to use reflection since the database may use different names
			PublicationImpl publication = new PublicationImpl();
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
	
		String sql = sqlHeader + "where lower(title) like lower(:title)";
		
		SqlParameterSource namedParameters = new MapSqlParameterSource("title", title);
		return new NamedParameterJdbcTemplate(datasource).query(sql, namedParameters, new PublicationRowMapper());
	
	}


	public List<Long> getPublicationIdsByAuthor(String authorLastName) {

		String sql = "SELECT pubs.resource_id from nextprot.publications pubs inner join nextprot.pubauthors authors on (pubs.resource_id = authors.publication_id) where lower(authors.last_name) = lower(:author_name)";

		SqlParameterSource namedParameters = new MapSqlParameterSource("author_name", authorLastName);
		return new NamedParameterJdbcTemplate(datasource).queryForList(sql, namedParameters, Long.class);
	}
}
