package com.genebio.nextprot.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genebio.nextprot.domain.Publication;

/**
 * @author dteixeira
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PublicationTest {
	
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private AuthorService authorService;

	@Test
	public void shouldGetPublicationById() {
		assertEquals(publicationService.getPublicationById(6634104).getId(), 6634104);
	}
	
	@Test
	public void shouldGetPublicationByTitle() {
		List<Publication> pubs = publicationService.getPublicationByTitle("%Correction%");
		assertEquals(pubs.size(), 90);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowAnIllegalArgumentException() {
		publicationService.getPublicationByTitle(null);
	}
	
	@Test
	public void shouldFireCache() {
		long startTime = System.currentTimeMillis();
		publicationService.getPublicationIdsByAuthor("%");
		long endTime = System.currentTimeMillis();

		System.out.println("Took " + (endTime - startTime));

		// should be in cache now
		startTime = System.currentTimeMillis();
		publicationService.getPublicationIdsByAuthor("%");
		endTime = System.currentTimeMillis();
		System.out.println("Took " + (endTime - startTime));
		
	}
	
}
