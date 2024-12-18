package com.aem.prepare.core.models;

import java.util.Date;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MultifieldHelper {
	private static final Logger LOG = LoggerFactory.getLogger(MultifieldHelper.class);
	private String bookName;
	private String bookSubject;
	private Date publishDate;
	private int copies;
	private List<NestedHelper> bookEditions;
	
	public MultifieldHelper(Resource resource) {
		
		try {
			if(resource.getValueMap().get("bookname", String.class)!=null) {
				this.bookName = resource.getValueMap().get("bookname", String.class);
			}
			if(resource.getValueMap().get("booksubject", String.class)!=null) {
				this.bookSubject = resource.getValueMap().get("booksubject", String.class);
			}
			if(resource.getValueMap().get("publishdate", String.class)!=null) {
				this.publishDate = resource.getValueMap().get("publishdate", Date.class);
			}
			if(resource.getValueMap().get("copies", String.class)!=null) {
				this.copies = resource.getValueMap().get("copies", Integer.class);
			}
		} catch (Exception e) {
			LOG.info("\n BEAN Error : {}",e.getMessage());
			
		}

	}

	public List<NestedHelper> getBookEditions() {
		return bookEditions;
	}

	public void setBookEditions(List<NestedHelper> bookEditions) {
		this.bookEditions = bookEditions;
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookSubject() {
		return bookSubject;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public int getCopies() {
		return copies;
	}
	

}
