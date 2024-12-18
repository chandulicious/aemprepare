package com.aem.prepare.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class)
@SlingServletPaths(
		value = {"/bin/executeworkflow","/prepare/executeworkflow"}
)

public class ExecuteWorkflow extends SlingSafeMethodsServlet{

	private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteWorkflow.class);
	
	
	@Override
	protected void doGet(final SlingHttpServletRequest req, final SlingHttpServletResponse res) throws IOException {
		String status = "Workflow executing";
		final ResourceResolver resourceResolver = req.getResourceResolver();
		String payload = req.getRequestParameter("page").toString();
		try {
			if(StringUtils.isNotBlank(payload)) {
				WorkflowSession workflowSession = resourceResolver.adaptTo(WorkflowSession.class);
				WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/prepare-page-version");
				WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);
				status = workflowSession.startWorkflow(workflowModel, workflowData).getState();
				
				}
		} catch (Exception e) {
			LOGGER.info("\n ERROR in Workflow: {}", e.getMessage());
		}
		res.setContentType("application/json");
		res.getWriter().write(status);
		
	}
}
