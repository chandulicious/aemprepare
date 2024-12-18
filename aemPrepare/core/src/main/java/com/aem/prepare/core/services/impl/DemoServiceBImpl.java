package com.aem.prepare.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.lucene.queryparser.flexible.messages.Message;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.tally.client.api.Response;
import com.aem.prepare.core.services.DemoService;
import com.aem.prepare.core.services.DemoServiceB;
import com.aem.prepare.core.services.MultiService;
import com.day.cq.wcm.api.Page;

@Component(service = DemoServiceB.class,immediate = true)

public class DemoServiceBImpl implements DemoServiceB{
	 private static final Logger LOG= LoggerFactory.getLogger(DemoServiceBImpl.class);
	 	 
	 //@Reference
	 //DemoService demoService;
	 
	 @Reference(target = "(component.name=com.aem.prepare.core.services.impl.MultiServiceBImpl)")
	 MultiService multiService;
	 
	 @Override
	 public String getNameWithReference() {
		 return "Response coming from " + multiService.getName();
	 }
/*
	 @Override
	 public List<String> getPagesList() {
		List<String> listPage = new ArrayList<String>();
		
		try {
			
			Iterator<Page> pages = demoService.getPagesB();
			
			while (pages.hasNext()) {
				listPage.add(pages.next().getTitle());
			}
			
			return listPage;
			
		} catch (Exception e) {
			LOG.info("\n ERROR in Service B",e.getMessage());
		}
		return null;
	}*/

}
