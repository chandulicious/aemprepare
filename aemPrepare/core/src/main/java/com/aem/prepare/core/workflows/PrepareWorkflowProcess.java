package com.aem.prepare.core.workflows;


import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class,
			immediate = true,
			property = {
					"process.label"+ "= Prepare Workflow Process",
					Constants.SERVICE_VENDOR + "= AEM Prepare",
					Constants.SERVICE_DESCRIPTION + "= AEM PREPARE WORKFLOW Process"
			})

public class PrepareWorkflowProcess implements WorkflowProcess{

	private static final Logger log = LoggerFactory.getLogger(PrepareWorkflowProcess.class);
	
	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments) throws WorkflowException {
		// workflowsession => used to get any other object like RR and the workflow process is done through session
		//MetaDataMap => If we pass any info through the workflow dialog, that info will be available in Meta data map
		//WorkflowItem => the payload and other imp info which flowing from starting of the wrokflow will be available in workItem. We can add our own custom data as well
		log.info("\n =====================================");
		try {
			WorkflowData workflowData =workItem.getWorkflowData();
			if(workflowData.getPayloadType().equals("JCR_PATH")) {
				Session session = workflowSession.adaptTo(Session.class);
				String path = workflowData.getPayload().toString() + "/jcr:content";
				
				Node node = (Node) session.getItem(path);
				String[] processArg = processArguments.get("PROCESS_ARGS", "string").toString().split(",");
				MetaDataMap wfd = workItem.getWorkflow().getWorkflowData().getMetaDataMap();

				for(String wfArg : processArg) {
					String[] args = wfArg.split(":");
					String prop = args[0];
					String value = args[1];
					
					if(node!=null) {
						wfd.put(prop, value);
						node.setProperty(prop, value);
					}
				}
				Set<String> keyset = wfd.keySet();
				Iterator<String> i = keyset.iterator();
				while (i.hasNext()) {
					String key = i.next();
					log.info("\n ITEM KEY - {} , Value - {}" , key,wfd.get(key));
					
				}
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
