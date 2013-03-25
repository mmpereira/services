package com.genebio.nextprot.service;

import java.util.List;

import com.genebio.nextprot.domain.Author;

public interface AuthorService {

	/**
	 * Gets a list of authors by publication id
	 * 
	 * @param title
	 * @return
	 */
	public List<Author> getAuthorByPublicationId(long publicationId);

}
