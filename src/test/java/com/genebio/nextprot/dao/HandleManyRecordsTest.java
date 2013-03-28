package com.genebio.nextprot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
/**
 * This is an example on how to process a huge amount of data coming from the database
 * 
 * 1) Set an approriate fetch size for the query
 * 2) Return Void (null) to avoid memory consumption, you can still make it return something, but be aware with the memory consumption...)
 * 
 * Don't forget to set autocommit to false in the datasource / connection settings, otherwise the fetch size property will be ignored and everything will come out from the database !!!
 * 
 * 
 * 
 * @author dteixeira
 *
 */
public class HandleManyRecordsTest {

	@Autowired
	private DataSource datasource;

	private String sql = "select concat('an-cv:',a.annotation_id) as id, a.identifier_id as entry_id, a.cv_annotation_type_id as annotation_type_id, cv.cv_name as annotation_type_name, a.cv_term_id as term_id, t.cv_name as term_name, tc.cv_name as term_cat, cast (1 as int8) as term_count " +
			"from nextprot.annotations a " +
			"inner join nextprot.sequence_identifiers si on (a.identifier_id=si.identifier_id and si.cv_type_id=1 and si.cv_status_id=1)" +
			"inner join nextprot.cv_terms cv on (cv.cv_id=a.cv_annotation_type_id) " +
			"inner join nextprot.cv_terms t on (t.cv_id=a.cv_term_id) " +
			"inner join nextprot.cv_term_categories tc on (t.cv_category_id=tc.cv_id)";

	private static class StringMapper implements ParameterizedRowMapper<Void> { //Return void here

		public Void mapRow(ResultSet resultSet, int row) throws SQLException {
			
			if(row % 1000 == 0){
				//System.out.println("Do some stuff");
			}
			
			return null;
		}
	};

	@Test
	public void getAuthorByPublicationId() {

		JdbcTemplate temp = new JdbcTemplate(datasource);
		temp.setFetchSize(100); //Don't forget to set the fecth size
		temp.query(sql, new StringMapper());
	}
	

}
