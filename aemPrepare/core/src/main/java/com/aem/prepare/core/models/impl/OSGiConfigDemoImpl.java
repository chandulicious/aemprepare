package com.aem.prepare.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.aem.prepare.core.models.OSGiConfigDemo;
import com.aem.prepare.core.services.OSGIConfigModule;
import com.aem.prepare.core.services.OSGiConfig;
import com.aem.prepare.core.services.OSGiFactoryConfig;

@Model(
		adaptables = SlingHttpServletRequest.class,
		adapters = OSGiConfigDemo.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class OSGiConfigDemoImpl implements OSGiConfigDemo{

	@OSGiService
	OSGiConfig osGiConfig;
	
	@OSGiService
	OSGIConfigModule osgiConfigModule;
	
	@OSGiService
	OSGiFactoryConfig osGiFactoryConfig;

	@Override
	public String getServiceName() {
		return osGiConfig.getServiceName();
	}

	@Override
	public int getServiceCount() {
		return osGiConfig.getServiceCount();
	}

	@Override
	public boolean getLiveData() {
		return osGiConfig.getLiveData();
	}

	@Override
	public String[] getCountries() {
		return osGiConfig.getCountries();
	}

	@Override
	public String getRunModes() {
		return osGiConfig.getRunModes();
	}

	@Override
	public String getServiceNameModule() {
		return osgiConfigModule.getServiceName();
	}

	@Override
	public int getServiceId() {
		return osgiConfigModule.getServiceId();
	}

	@Override
	public String getServiceURL() {
		return osgiConfigModule.getServiceURL();
	}
	
	@Override
	public List<OSGiFactoryConfig> getAllOsgiConfigs(){
		return osGiFactoryConfig.getAllConfigs();
	}
	
}
