package com.aem.prepare.core.listeners;

import java.util.List;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*ResourceChangeListener are registered with the framework service registry and are notified with ResourceChange objects to determine the type of change,location and other properties
ResourceChangeListener must be registered with a service property PATHS whose value is the list of resource paths for which the listener will listen to
ResourceChangeListener must be registered with a service property CHANGES whose value is the list of event types for which the listener will listen to*/

//@Component(service = ResourceChangeListener.class,
//immediate = true,
//property = {
//        ResourceChangeListener.PATHS + "=/content/aemPrepare/dfd",
//        ResourceChangeListener.CHANGES + "=ADDED",
//        ResourceChangeListener.CHANGES + "=REMOVED",
//        ResourceChangeListener.CHANGES + "=CHANGED"
//})
public class ResourceEventHandling implements ResourceChangeListener{
//	private static final Logger LOG = LoggerFactory.getLogger(ResourceEventHandling.class);
//
//	@Override
	public void onChange(List<ResourceChange> list) {//FRAMEWORK PROVIDES LIST OF RESOURCE CHANGE OBJECTS
//		LOG.info("Inside SLing EH");
		
	}

}
