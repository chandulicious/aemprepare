package com.aem.prepare.core.services;

public interface OSGiConfig {
	public String getServiceName();
	public int getServiceCount();
	public boolean getLiveData();
	public String[] getCountries();
	public String getRunModes();
}
