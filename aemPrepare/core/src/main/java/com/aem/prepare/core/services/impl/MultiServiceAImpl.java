package com.aem.prepare.core.services.impl;

import org.osgi.service.component.annotations.*;
import org.osgi.service.component.propertytypes.ServiceRanking;

import com.aem.prepare.core.services.MultiService;

@Component(service = MultiService.class,immediate = true,name="ServiceA")
@ServiceRanking(1000)
public class MultiServiceAImpl implements MultiService{

	@Override
	public String getName() {
		return "MultiServiceA";
	}

}
