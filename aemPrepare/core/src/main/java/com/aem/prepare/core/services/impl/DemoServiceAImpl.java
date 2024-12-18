package com.aem.prepare.core.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.jcr.api.SlingRepository;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.osgi.service.component.annotations.*;
import org.osgi.service.component.ComponentContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.aem.prepare.core.utils.ResolverUtil;
import com.aem.prepare.core.services.DemoService;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = DemoService.class,immediate = true)
public class DemoServiceAImpl implements DemoService{
	 private static final Logger LOG= LoggerFactory.getLogger(DemoServiceAImpl.class);

	    @Reference
	    private ResourceResolverFactory resourceResolverFactory;

	    @Activate
	    public void activate(ComponentContext componentContext){
	        LOG.info("\n ==============INSIDE ACTIVATE================");
	        LOG.info("\n {} = {} ",componentContext.getBundleContext().getBundle().getBundleId(),componentContext.getBundleContext().getBundle().getSymbolicName());
	    }

	    @Deactivate
	    public void deactivate(ComponentContext componentContext){
	        LOG.info("\n ==============INSIDE DEACTIVATE================");
	    }

	    @Modified
	    public void modified(ComponentContext componentContext){
	        LOG.info("\n ==============INSIDE MODIFIED================");
	    }
	    
	    @Override
	    public String getPages(){
	        try {
	            LOG.info("===============inside getPages============");
	            return "PAGES";
	        } catch (Exception e) {
	            LOG.info("\n Login Exception {} ",e.getMessage());
	        }
	        return null;
	    }
	    @Override
	    public Iterator<Page> getPagesB(){
	    	
	    	try {
	    		LOG.info("\n INSIDE GETPAGEB");
	    		ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
	            PageManager pageManager=resourceResolver.adaptTo(PageManager.class);
	            Page page=pageManager.getPage("/content/aemPrepare/home");
	            LOG.info("pagemanager: {}",pageManager);
	            Iterator<Page> pages=page.listChildren();
	            LOG.info("pages:{}",pages);
	            return pages;
	        } catch (Exception e) {
	            LOG.info("\n Login Exception {} ",e.getMessage());
	        }
	        return null;
	    }

}
