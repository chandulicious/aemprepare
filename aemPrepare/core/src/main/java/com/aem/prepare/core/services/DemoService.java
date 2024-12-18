package com.aem.prepare.core.services;
import com.day.cq.wcm.api.Page;

import java.util.Iterator;
import java.util.List;
public interface DemoService {
	public String getPages();
	public Iterator<Page> getPagesB();
}
