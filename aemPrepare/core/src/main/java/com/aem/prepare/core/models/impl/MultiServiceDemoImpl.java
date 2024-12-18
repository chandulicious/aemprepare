package com.aem.prepare.core.models.impl;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.prepare.core.models.MultiServiceDemo;
import com.aem.prepare.core.models.ServiceDemo;
import com.aem.prepare.core.services.DemoServiceB;
import com.aem.prepare.core.services.MultiService;

@Model(
		adaptables = SlingHttpServletRequest.class,
		adapters = MultiServiceDemo.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiServiceDemoImpl implements MultiServiceDemo{

	private static final Logger LOG = LoggerFactory.getLogger(MultiServiceDemoImpl.class);
	
	//@OSGiService(filter = "(component.name=com.aem.prepare.core.services.impl.MultiServiceBImpl)")
	//@OSGiService
	//MultiService multiService;
	
	@OSGiService(filter = "(component.name=com.aem.prepare.core.services.impl.MultiServiceBImpl)")
	//@OSGiService
	MultiService multiServiceB;
	
	@OSGiService(filter = "(component.name=ServiceA)")
	//@OSGiService
	MultiService multiServiceA;
	
	 @OSGiService
	 DemoServiceB demoServiceB;
	 
	 
		//@Override
		//public String getNameFromService() {
			//return multiService.getName();
		//} 
	@Override
	public String getNameFromServiceA() {
		return multiServiceA.getName();
	}
	
	@Override
	public String getNameFromServiceB() {
		return multiServiceB.getName();
	}
	
	@Override
    public String getNameWithReference() {
        return demoServiceB.getNameWithReference();
    }
	
}
