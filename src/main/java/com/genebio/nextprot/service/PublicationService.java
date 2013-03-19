package com.genebio.nextprot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.genebio.nextprot.domain.Publication;
import com.sun.istack.internal.NotNull;

public interface PublicationService {
	
	/**
	 * Gets publication by id
	 * @param id
	 * @return
	 */
	public Publication getPublicationById(@Value("publicationId") long id);

	/**
	 * Gets publication by title case insensitive
	 * @param title
	 * @return
	 */
	public List<Publication> getPublicationByTitle(@NotNull @Value("title") String title);

}
