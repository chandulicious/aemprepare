package com.aem.prepare.core.services.impl;

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

@Component(immediate = true,service = Runnable.class)
@Designate(ocd = SchedulerConfiguration.class)

public class AemScheduler implements Runnable{

	private static final Logger LOG = LoggerFactory.getLogger(AemScheduler.class);
	
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
		ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n=============SCHEDULER ADDED==============");
		ScheduleOptions schedulerOptionsNow = scheduler.NOW(3,5);
		scheduler.schedule(this, schedulerOptionsNow);
		
	}
	public void run() {
		LOG.info("\n ============RUN METHOD EXECUTED===================");
	}


}
