package com.aem.prepare.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

import com.aem.prepare.core.services.OSGiConfig;

@Component(service = OSGiConfig.class,immediate = true)
@Designate(ocd=OSGiConfigImpl.ServiceConfig.class)
public class OSGiConfigImpl implements OSGiConfig{
	
	@ObjectClassDefinition(name = "AEM-Prepare OSGI configuration",
			description="OSGI COnfig Demo")
	
	public @interface ServiceConfig{
		
		@AttributeDefinition(
				name = "Service Name",
				description = "Enter Service Name",
				type = AttributeType.STRING)
		public String getServiceName() default "AEM Prepare Service";
		
		@AttributeDefinition(
				name = "Service Count",
				description = "Add Service Count",
				type = AttributeType.INTEGER)
		int getServiceCount() default 5;
		
		@AttributeDefinition(
				name = "Live Data",
				description = "check this to get live data.",
				type = AttributeType.BOOLEAN)
		boolean getLiveData() default false;
		
		@AttributeDefinition(
				name = "Countries",
				description = "Add Countries locales for which you want to run this service",
				type = AttributeType.STRING)
		String[] getCountries() default {"de","in"};
		
		@AttributeDefinition(
				name = "Run Modes",
				description = "Select a Run Mode",
				options = {
						@Option(label = "Author",value = "author"),
						@Option(label = "Publish",value = "publish"),
						@Option(label = "Both",value = "both")
				},
				type = AttributeType.STRING)
		String getRunModes() default "both";
		
		
	}
	
	private String serviceName;
	private int serviceCount;
	private boolean liveData;
	private String[] countries;
	private String runModes;
	
	@Activate
	protected void Activate(ServiceConfig serviceConfig) {
		serviceName = serviceConfig.getServiceName();
		serviceCount = serviceConfig.getServiceCount();
		liveData = serviceConfig.getLiveData();
		countries = serviceConfig.getCountries();
		runModes = serviceConfig.getRunModes();
	}

	@Override
	public String getServiceName() {
		return serviceName;
	}

	@Override
	public int getServiceCount() {
		return serviceCount;
	}

	@Override
	public boolean getLiveData() {
		return liveData;
	}

	@Override
	public String[] getCountries() {
		return countries;
	}

	@Override
	public String getRunModes() {
		return runModes;
	}

}
