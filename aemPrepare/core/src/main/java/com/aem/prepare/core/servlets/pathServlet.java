package com.aem.prepare.core.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.request.RequestParameter;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class,immediate = true)
/*property={
		Constants.SERVICE_DESCRIPTION + "=Simple Demo Servlet",
		"sling.servlet.methods="+ HttpConstants.METHOD_GET,
        "sling.servlet.paths="+ "/aem/pages"
}
	)	*/
@SlingServletPaths(
		value={"/bin/pages","/aemPrepare/pages"}
		)
public class pathServlet extends SlingSafeMethodsServlet{
	private static final Logger LOG= LoggerFactory.getLogger(pathServlet.class);

    private static final long serialVersionUid = 1L;

    @Override
    protected void doGet(final SlingHttpServletRequest req,
            final SlingHttpServletResponse resp) throws ServletException, IOException {
        final ResourceResolver resourceResolver = req.getResourceResolver();
        Page page = resourceResolver.adaptTo(PageManager.class).getPage("/content/aemPrepare");
        JsonArray pagesArray = new JsonArray();
        try {
			Iterator<Page> childPages = page.listChildren();
			while (childPages.hasNext()) {
				Page childPage = childPages.next();
				JsonObject pageObject = new JsonObject();
				pageObject.addProperty(childPage.getTitle(), childPage.getPath().toString());
				pagesArray.add(pageObject);
			}
		} catch (JsonIOException e) {
			LOG.info("\n ERROR in JSON ", e.getMessage());
		}
        resp.setContentType("application/json");
        resp.getWriter().write(pagesArray.toString());
    }
    
}
