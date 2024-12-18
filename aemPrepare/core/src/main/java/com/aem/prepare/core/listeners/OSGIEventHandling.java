package com.aem.prepare.core.listeners;

import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Event handler are registered with the framework service registry and are notified with an event object
EventHandler can inspect the recived event object(framework will give this event object) to determine its topics and properties
eventhandler objects must be registered with a service property EventConstants.EVENT_TOPIC whose value is the list of topics for which the event handler will listen to
Event handler can also be registered with an EventConstants.EVENT_FILTER service property to further filter the events.*/

//@Component(immediate = true,service = EventHandler.class,
//property = {
//		EventConstants.EVENT_TOPIC +"=org/apache/sling/api/resource/Resource/ADDED",
//		EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/CHANGED",
//		EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/REMOVED",
//		EventConstants.EVENT_FILTER+"=/content/aemPrepare/home/cart/*"
//})
public class OSGIEventHandling implements EventHandler {
//	private static final Logger LOG = LoggerFactory.getLogger(OSGIEventHandling.class);
	@Override
	public void handleEvent(Event event) {//this event object provided by framework has all info under one object but for JCR EH each event has one obejct, which is why Iterator is used. Hence OSGI EH is preferred over JCR
//		LOG.info("\n Resource Event: {} at : {}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
//		LOG.info("\n Resource Event called");
	}

}
