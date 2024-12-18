package com.aem.prepare.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.jcr.JcrConstants;

@Component(service = Servlet.class,immediate = true)
@SlingServletResourceTypes(
		resourceTypes = "prepare/components/structure/page",
		methods = {HttpConstants.METHOD_POST,HttpConstants.METHOD_GET},
		selectors = {"prepare","aem"},
		extensions = {"txt","xml"})
public class PrepareResourceTypeServlet extends SlingAllMethodsServlet{
	
	private static final Logger LOG = LoggerFactory.getLogger(PrepareResourceTypeServlet.class);
	
    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final Resource resource = req.getResource();
        resp.setContentType("text/plain");
        resp.getWriter().write("Page Title = " + resource.getValueMap().get(JcrConstants.JCR_TITLE));
    }
	
    @Override
    protected void doPost(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        try {
        	List<RequestParameter> requestParameterList = req.getRequestParameterList();
            
            for (RequestParameter requestParameter : requestParameterList) {
    			LOG.info("\n PARAMETERS: => {} : {}",requestParameter.getName(),requestParameter.getString());
    		}
		} catch (Exception e) {
			LOG.info("\n ERROR IN REQUEST",e.getMessage());
		}
        resp.getWriter().write("======FORM SUBMITTED==========");;
    }
}
