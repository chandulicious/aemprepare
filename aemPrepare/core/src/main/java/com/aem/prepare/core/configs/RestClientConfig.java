package com.aem.prepare.core.configs;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "LAB2020 - Rest Client Configuration")
public @interface RestClientConfig {
	
	int DEFAULT_CONN_TIMEOUT = 5000;
	int DEFAULT_READ_TIMEOUT = 30000;
	int DEFAULT_CONN_POOL_SIZE = 10;
	int DEFAULT_NUMBER_OF_RETRIES = 8;
	int DEFAULT_RETRY_INTERVAL = 5000;
	
	@AttributeDefinition(name = "Endpoint", description = "Web service endpoint")
	String getEndpoint();
	
	@AttributeDefinition(name = "Connection timeout", description = "Connection time out (typically 5000ms or less)", type = AttributeType.INTEGER)
    int getConnectTimeout() default DEFAULT_CONN_TIMEOUT;
 
    @AttributeDefinition(name = "Read Timeout", description = "Read time out (typically 30000ms) after connection is established", type = AttributeType.INTEGER)
    int getReadTimeout() default DEFAULT_READ_TIMEOUT;
 
    @AttributeDefinition(name = "Web service connection pool size", type = AttributeType.INTEGER)
    int getPoolSize() default DEFAULT_CONN_POOL_SIZE;
 
    @AttributeDefinition(name = "List of Handled errors", description = "Configure here all the errors handled by the http client, e.g 504")
    int[] getListOfHandledErrors();
 
    @AttributeDefinition(name = "Web service number of retries", type = AttributeType.INTEGER)
    int getNumberOfRetries() default DEFAULT_NUMBER_OF_RETRIES;
 
    @AttributeDefinition(name = "Retry interval", description = "Value in milliseconds", type = AttributeType.INTEGER)
    int getRetryInterval() default DEFAULT_RETRY_INTERVAL;

}
