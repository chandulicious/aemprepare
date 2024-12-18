package com.aem.prepare.core.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.metatype.annotations.Designate;

import com.aem.prepare.core.configs.AemOsgiFactoryConfig;
import com.aem.prepare.core.services.OSGiFactoryConfig;


@Component(service = OSGiFactoryConfig.class,configurationPolicy = ConfigurationPolicy.REQUIRE)
@Designate(ocd = AemOsgiFactoryConfig.class,factory = true)
public class OSGiFactoryConfigImpl implements OSGiFactoryConfig{

	private int configID;
	private String serviceName;
	private String serviceURL;
	List<OSGiFactoryConfig> configList;
	@Activate
	@Modified
	protected void activate(final AemOsgiFactoryConfig aemPrepareConfig) {
		configID = aemPrepareConfig.configId();
		serviceName = aemPrepareConfig.serviceName();
		serviceURL = aemPrepareConfig.serviceURL();
	}
	
	@Reference(service = OSGiFactoryConfig.class,cardinality = ReferenceCardinality.MULTIPLE,policy = ReferencePolicy.DYNAMIC)
	public void bindOSGiFactoryConfig(final OSGiFactoryConfig config) {
		if(configList == null) {
			configList = new ArrayList<>();
		}
		configList.add(config);
	}
	
	public void unbindOSGiFactoryConfig(final OSGiFactoryConfig config) {
		configList.remove(config);
	}
	
	@Override
	public int getConfigID() {
		return configID;
	}
	
	@Override
	public String getServiceName() {
		return serviceName;
	}
	
	@Override
	public String getServiceURL() {
		return serviceURL;
	}
	
	@Override
	public List<OSGiFactoryConfig> getAllConfigs() {
		return configList;
	}
	
}
