package com.genebio.nextprot.template.engine;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

public class VelocityTemplateEngine implements TemplateEngine {

	private VelocityEngine ve = null;
	private Template publicationTemplate = null;

	public VelocityTemplateEngine() {

		try {
			ve = new VelocityEngine();
			publicationTemplate = ve.getTemplate("src/main/resources/templates/publications.vm");
			ve.init();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getXML(Object o) {

		VelocityContext context = new VelocityContext();
		context.put("publications", o);
		StringWriter writer = new StringWriter();

		try {
			this.publicationTemplate.merge(context, writer);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return writer.toString();

	}

	@Override
	public String getJSON(Object o) {
		// TODO Auto-generated method stub
		return null;
	}

}
