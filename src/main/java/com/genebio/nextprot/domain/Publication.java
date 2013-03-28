package com.genebio.nextprot.domain;

import java.util.Date;

public interface Publication {

	public long getId();
	public String getTitle();
	public String getAbstractText();
	
	public Date getPublicationDate();

}
