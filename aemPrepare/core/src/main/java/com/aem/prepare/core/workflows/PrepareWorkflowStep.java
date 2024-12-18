package com.aem.prepare.core.workflows;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class,
		property = {
				"process.label"+ "= Prepare Workflow Step",
				Constants.SERVICE_VENDOR + "= AEM PREPARe",
				Constants.SERVICE_DESCRIPTION + "= Prepare workflow step"
				
		})
public class PrepareWorkflowStep implements WorkflowProcess{
	
	private static final Logger log = LoggerFactory.getLogger(PrepareWorkflowStep.class);

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {

		log.info("\n =============================Custom Workflow Step=========================================");
	}

}
