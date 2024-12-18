package com.aem.prepare.core.services.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.commons.scheduler.Job;
import org.apache.sling.commons.scheduler.JobContext;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.prepare.core.configs.SchedulerConfiguration;

@Component(immediate = true,service = Job.class)
@Designate(ocd = SchedulerConfiguration.class)
public class AemSchedulerJob implements Job {
private static final Logger LOG = LoggerFactory.getLogger(AemSchedulerJob.class);
	
	private int schedulerId;
	
	@Reference
	private Scheduler scheduler;
	
	@Activate
	protected void activate(SchedulerConfiguration config) {
		schedulerId = config.schedulerName().hashCode();
		addScheduler(config);
	}
	
	@Deactivate
	protected void deactivate(SchedulerConfiguration config) {
		removeScheduler();
	}
	
	
	protected void removeScheduler() {
		scheduler.unschedule(String.valueOf(schedulerId));
	}
	
	private void addScheduler(SchedulerConfiguration config) {
		ScheduleOptions in = scheduler.EXPR("8 51 16 1/1 * ? *");
		Map<String, Serializable> inMap = new HashMap<>();
		inMap.put("country", "in");
		inMap.put("url", "www.in.com");
		in.config(inMap);
		scheduler.schedule(this, in);
		
		ScheduleOptions de = scheduler.EXPR("8 52 16 1/1 * ? *");
		Map<String, Serializable> deMap = new HashMap<>();
		deMap.put("country", "de");
		deMap.put("url", "www.de.com");
		de.config(deMap);
		scheduler.schedule(this, de);
		
		ScheduleOptions us = scheduler.EXPR("8 53 16 1/1 * ? *");
		Map<String, Serializable> usMap = new HashMap<>();
		usMap.put("country", "us");
		usMap.put("url", "www.us.com");
		us.config(usMap);
		scheduler.schedule(this, us);
		LOG.info("\n=============SCHEDULER ADDED==============");
	}
	@Override
	public void execute(JobContext jobContext) {
		LOG.info("\n ========> Country : URL {} : {}", jobContext.getConfiguration().get("country"),
				jobContext.getConfiguration().get("url"));
		
	}

}
