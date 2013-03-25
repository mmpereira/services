package com.genebio.nextprot.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.genebio.nextprot.domain.Publication;
import com.genebio.nextprot.template.engine.TemplateEngine;

/**
 * @author dteixeira
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/applicationContext.xml" })
public class PublicationTemplateEngineTest {

	@Autowired
	private PublicationService publicationService;

	@Autowired
	private TemplateEngine templateEngine;

	@Test
	public void shouldPrintAListOfPublicationsInXMLFormat() {
		List<Publication> pubs = publicationService.getPublicationByTitle("%Correction%");
		System.out.println(this.templateEngine.getXML(pubs));
	}
	
}
