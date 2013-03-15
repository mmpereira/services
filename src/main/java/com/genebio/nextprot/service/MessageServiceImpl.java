package com.genebio.nextprot.service;

import org.springframework.stereotype.Service;

import com.genebio.nextprot.domain.Message;

@Service
public class MessageServiceImpl implements MessageService {

	public Message getMessage() {
		return new Message("hello world!");
	}

}
