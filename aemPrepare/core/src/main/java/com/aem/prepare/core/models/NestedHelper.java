package com.aem.prepare.core.models;

import java.util.Date;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NestedHelper {
	private static final Logger LOG = LoggerFactory.getLogger(MultifieldHelper.class);
	private int bookEdition;
	private Date editionDate;
	
	
	public NestedHelper(Resource resource) {
		if(resource.getValueMap().get("bookedition", String.class)!=null) {
			this.bookEdition = resource.getValueMap().get("bookedition", Integer.class);
		}
		if(resource.getValueMap().get("editiondate", String.class)!=null) {
			this.editionDate = resource.getValueMap().get("editiondate", Date.class);
		}
	}
	public int getBookEdition() {
		return bookEdition;
	}
	public Date getEditionDate() {
		return editionDate;
	}
	
	
}
