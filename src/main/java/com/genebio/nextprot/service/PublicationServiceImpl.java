package com.genebio.nextprot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genebio.nextprot.dao.PublicationDAO;
import com.genebio.nextprot.domain.Publication;
import com.sun.istack.internal.NotNull;

@Component("publicationService")
public class PublicationServiceImpl implements PublicationService{

	@Autowired
	private PublicationDAO publicationDAO;
	
	public Publication getPublicationById(@NotNull long id){
		return publicationDAO.getPublicationById(id);
	}

	@Override
	public List<Publication> getPublicationByTitle(String title) {
		return publicationDAO.getPublicationByTitle(title);
	}

}
