package com.aem.prepare.core.services;

import java.util.List;

public interface OSGiFactoryConfig {
	public int getConfigID();
	public String getServiceName();
	public String getServiceURL();
	List<OSGiFactoryConfig> getAllConfigs();
	}
