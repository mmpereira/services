package com.genebio.nextprot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.genebio.nextprot.aop.annotation.NotNullArg;
import com.genebio.nextprot.domain.Publication;

public interface PublicationService {
	
	/**
	 * Gets publication by id
	 * @param id
	 * @return
	 */
	public Publication getPublicationById(@NotNullArg @Value("publicationId") long id);

	/**
	 * Gets publication by title case insensitive
	 * @param title
	 * @return
	 */
	public List<Publication> getPublicationByTitle(@NotNullArg @Value("title") String title);

}
