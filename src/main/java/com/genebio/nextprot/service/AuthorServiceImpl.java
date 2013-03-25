package com.genebio.nextprot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.genebio.nextprot.dao.AuthorDAO;
import com.genebio.nextprot.domain.Author;

@Component("authorSrvice")
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDAO authorDAO;

	@Override
	public List<Author> getAuthorByPublicationId(long id) {
		return authorDAO.getAuthorByPublicationId(id);
	}

}
