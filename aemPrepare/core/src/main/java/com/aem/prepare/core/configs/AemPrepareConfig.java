package com.aem.prepare.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
		name = "AEM Prepare configuration",
		description = "Configuration for aem")
public @interface AemPrepareConfig {
	
	@AttributeDefinition(name = " service id", description = "service id" , type = AttributeType.INTEGER)
	public int serviceId();
	
	@AttributeDefinition(name = " service name", description = "service name" , type = AttributeType.STRING)
	public String serviceName();
	
	@AttributeDefinition(name = " service URL", description = "service URL" , type = AttributeType.STRING)
	public String serviceURL();

}
