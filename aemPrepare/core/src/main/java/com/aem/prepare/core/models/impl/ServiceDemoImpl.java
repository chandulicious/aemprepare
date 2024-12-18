package com.aem.prepare.core.models.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.prepare.core.models.ServiceDemo;
import com.aem.prepare.core.services.DemoService;
import com.day.cq.wcm.api.Page;


@Model(
		adaptables = SlingHttpServletRequest.class,
		adapters = ServiceDemo.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ServiceDemoImpl implements ServiceDemo{
	
	private static final Logger LOG = LoggerFactory.getLogger(ServiceDemoImpl.class);
		
	@OSGiService
	DemoService demoService;

	@Override
	public String getPageList() {
		return demoService.getPages();
	}
	
	@Override
	public Iterator<Page> getPagesListB() {
		return demoService.getPagesB();
	}
	@PostConstruct
	protected void init() {
		
	}
}
