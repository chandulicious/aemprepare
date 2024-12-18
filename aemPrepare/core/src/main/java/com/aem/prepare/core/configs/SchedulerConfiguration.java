package com.aem.prepare.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(
		name = "AEM Prepare - Scheduler Configuration",
		description = "Sling Scheduler Configuration"
		)
public @interface SchedulerConfiguration {
	
	@AttributeDefinition(
			name = "Scheduler name",
			description = "Name of the Scheduler",
			type = AttributeType.STRING)
	public String schedulerName() default "Custom Sling Scheduler Configuration";
	
	@AttributeDefinition(
			name = "Cron Expression",
			description = "Cron Expression used by the Scheduler",
			type = AttributeType.STRING)
	public String cronExpression() default "0 0/1 * 1/1 * ? *"; //runs every 18 seconds
	
}
