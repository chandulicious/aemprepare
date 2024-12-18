package com.aem.prepare.core.listeners;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.ResourceUtil;
import org.apache.sling.event.jobs.Job;
import org.apache.sling.event.jobs.consumer.JobConsumer;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.prepare.core.utils.ResolverUtil;

//
//@Component(immediate = true,service = JobConsumer.class,
//property = {
//		JobConsumer.PROPERTY_TOPICS+"=aem.jobs"
//})
public class AemJobsConsumer implements JobConsumer {

//	private static final Logger LOG = LoggerFactory.getLogger(AemJobsConsumer.class);
//
//	@Reference
//	ResourceResolverFactory resourceResolverFactory;
//	
	@Override
	public JobResult process(Job job) {
//		try {
//			ResourceResolver resourceResolver = ResolverUtil.newResolver(resourceResolverFactory);
//			String path = (String)job.getProperty("path");
//			String event = (String)job.getProperty("event");
//			String heropage = (String)job.getProperty("heropage");
//			LOG.info("\n Job executing for : {}", resourceResolver.getResource(heropage).getName());
//			return JobResult.OK;
//		} catch (Exception e) {
//			LOG.info("\n ERROR in Job consumer", e.getMessage());
//			return JobResult.FAILED;
//		}
		return null;
	}

}
