package com.genebio.nextprot.service;

import java.util.List;

import com.genebio.nextprot.domain.Publication;

public interface PublicationService {
	
	/**
	 * Gets publication by id
	 * @param id
	 * @return
	 */
	public Publication getPublicationById(long id);

	/**
	 * Gets publication by title case insensitive
	 * @param title
	 * @return
	 */
	public List<Publication> getPublicationByTitle(String title);

}
