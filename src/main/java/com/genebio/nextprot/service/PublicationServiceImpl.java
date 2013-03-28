package com.genebio.nextprot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.genebio.nextprot.dao.PublicationDAO;
import com.genebio.nextprot.domain.Publication;

@Component
public class PublicationServiceImpl implements PublicationService {

	@Autowired
	private PublicationDAO publicationDAO;

	public Publication getPublicationById(@Value("id") long id) {
		return publicationDAO.getPublicationById(id);
	}

	@Override
	public List<Publication> getPublicationByTitle(String title) {
		return publicationDAO.getPublicationByTitle(title);
	}
	
	
	@Override
	@Cacheable(value="publications")	
	public List<Long> getPublicationIdsByAuthor(String authorLastName) {
		return publicationDAO.getPublicationIdsByAuthor(authorLastName);
	}

}
