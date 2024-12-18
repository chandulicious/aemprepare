package com.aem.prepare.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.prepare.core.configs.AemPrepareConfig;
import com.aem.prepare.core.services.OSGIConfigModule;


@Component(service = OSGIConfigModule.class,immediate = true)
@Designate(ocd=AemPrepareConfig.class)
public class OSGIConfigModuleImpl implements OSGIConfigModule{

	private int serviceId;
	private String serviceName;
	private String ServiceURL;
	
	@Activate
	protected void activate(AemPrepareConfig aemPrepareConfig) {
		serviceId = aemPrepareConfig.serviceId();
		serviceName = aemPrepareConfig.serviceName();
		ServiceURL = aemPrepareConfig.serviceURL();
	}
	
	@Override
	public int getServiceId() {
		return serviceId;
	}
	
	@Override
	public String getServiceName() {
		return serviceName;
	}
	
	@Override
	public String getServiceURL() {
		return ServiceURL;
	}
	
	
}
