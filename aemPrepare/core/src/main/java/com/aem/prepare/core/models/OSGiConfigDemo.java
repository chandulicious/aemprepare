package com.aem.prepare.core.models;

import java.util.List;

import com.aem.prepare.core.services.OSGiFactoryConfig;

public interface OSGiConfigDemo {
	public String getServiceName();
	public int getServiceCount();
	public boolean getLiveData();
	public String[] getCountries();
	public String getRunModes();
	public String getServiceNameModule();
	public int getServiceId();
	public String getServiceURL();
	public List<OSGiFactoryConfig> getAllOsgiConfigs();
}
