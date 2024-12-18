package com.aem.prepare.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

	@ObjectClassDefinition(name = "AEM-Prepare - OSGI Factory configuration",
			description="OSGI  COnfig Factory Demo")
	
	public @interface AemOsgiFactoryConfig{
		
		@AttributeDefinition(
				name = "Config ID",
				description = "Enter Config ID",
				type = AttributeType.INTEGER)
		int configId();
		
		@AttributeDefinition(
				name = "Service Name",
				description = "Add Service Name",
				type = AttributeType.STRING)
		String serviceName() default "service#";
		
		@AttributeDefinition(
				name = "Service URL",
				description = "Add service URL",
				type = AttributeType.STRING)
		String serviceURL() default "URL #";
		
		
	}
