package com.aem.prepare.core.servlets;




import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.scribe.model.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.social.tally.client.api.Response;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.PageManagerFactory;
import com.day.cq.wcm.api.WCMException;

@Component(service = {Servlet.class},
		property = {
				"sling.servlet.methods = GET",
				"sling.servlet.paths = /bin/createpage"
		})

public class CreatePageServlet extends SlingSafeMethodsServlet{
    private static final Logger log = LoggerFactory.getLogger(CreatePageServlet.class);

	@Reference
	PageManagerFactory pageManagerFactory;
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
	
		try {
			PageManager pageManager = pageManagerFactory.getPageManager(req.getResourceResolver());
			String parentPagePath = "/content/aemPrepare/fr";
			String newPageName = "new-page";
			String newPageTitle = "new page title";

			Page newPage = pageManager.create(parentPagePath, newPageName, null, newPageTitle, true);
			
			
			if(newPage != null) {
				log.info("New Page Created at path: " + newPage.getPath());
				res.getWriter().write("New Page Created at path: " + newPage.getPath());
			}else {
				log.error("Failed to create page");
				res.getWriter().write("Failed to create new page");
			}
			
		}
		catch (WCMException | IOException e) {
			// TODO: handle exception
			log.error("Error while creating new page:" + e.getMessage());
			res.setStatus(500);
			res.getWriter().write("Error while creating a new page");
		}
	}

}
