package com.aem.prepare.core.listeners;

import javax.jcr.LoginException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.observation.Event;
import javax.jcr.observation.EventIterator;
import javax.jcr.observation.EventListener;

import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*Event handling at JCR/Repository level
Event listener can be registered via observation manager object
Event listeners are notified asynchronously(if multiple events occur at same time, no event is given priority to execute first), and see events after they occur and the transaction committed
event listener only sees events for which the session that is registerd has sufficiet access rights*/

@Component(service = EventListener.class)
public class JCREventHandling implements EventListener{
	
	
//	private static final Logger LOG = LoggerFactory.getLogger(JCREventHandling.class);
//
//	//event listener should also be registered. Eventlistener should know what event to listen and what is the behaviour. For this we should register the listener using observation manager
//	
//	private Session session;
//	
//	@Reference
//	SlingRepository slingRepository;
//	
//	//register listener using observation manager
//	@Activate
//	public void activate() throws Exception {
//		session = slingRepository.loginService("aemprepare",null);// you will get the session using sling respository login service. Use your service user name. null => is a default workspace
//		//whatever the events/paths this session has permissions only those events will be notified
//		session.getWorkspace().getObservationManager().addEventListener(
//				this,//event handler (default which is written down onEvent()), if customized event handler is written that will be given in place of this
//				Event.NODE_ADDED | Event.PROPERTY_CHANGED, //int code for event type
//				"/content/aemPrepare/en", 
//				true,//isDeep? this listens to events that take part in sub paths also if it given as true
//				null,//UUIDs filter. If in case there any uinque id property for given path or under the path as a filter. Null means it listens for all without considering any filter. We can pass the value here in an array
//				null,// nodetype filter. null means it listens to all type of nodes without filter
//				false);//if it is true the session to which it is registered will be ignored. i.e aemprepare service user session user will be ignored on any event trigger if this value is true
//		
//		
//	}
//
//	
////	as soon as the eventlistener is implemented for the class onEvent method should be implemented
//
	@Override
	public void onEvent(EventIterator eventIterator) {//this method is a event handler
//		try {
//			while(eventIterator.hasNext() {
//				
//				LOG.info("\nEvent:{},Node:{}, ",eventIterator.nextEvent().getType(),eventIterator.nextEvent().getIdentifier());
//				LOG.info("Path:{}",eventIterator.nextEvent().getPath());
//			}
//			
//		} catch (Exception e) {
//			LOG.error("Error while processing events : {}", e.getMessage());
//		}
	}
}
