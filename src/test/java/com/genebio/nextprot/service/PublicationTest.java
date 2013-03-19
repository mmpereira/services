package com.genebio.nextprot.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.service.PublicationService;

/**
 * @author dteixeira
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PublicationTest {

	@Autowired
	private PublicationService publicationService;

	@Test
	public void testGetById() {
		assertEquals(publicationService.getPublicationById(6634104).getId(), 6634104);
	}

	@Test
	public void testGetByTitle() {
		List<Publication> pubs = publicationService.getPublicationByTitle("%Correction%");
		assertEquals(pubs.size(), 89);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testExceptionHandling() {
		publicationService.getPublicationByTitle(null);
	}

}
