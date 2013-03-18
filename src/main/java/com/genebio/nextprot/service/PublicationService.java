package com.genebio.nextprot.service;

import com.genebio.nextprot.domain.Publication;

public interface PublicationService {
	
	/**
	 * Gets publication by id
	 * @param id
	 * @return
	 */
	public Publication getPublicationById(long id);

}