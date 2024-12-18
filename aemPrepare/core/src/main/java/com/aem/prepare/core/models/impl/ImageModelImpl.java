package com.aem.prepare.core.models.impl;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.aem.prepare.core.models.ImageModel;

@Model(adaptables = Resource.class,
		adapters = ImageModel.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class ImageModelImpl implements ImageModel{
	@ValueMapValue
    private String fileReference;
    @ValueMapValue
    private String linkURL;
    @ValueMapValue
    private String description;
    @ValueMapValue
    private String alt;
    @ValueMapValue
    private String display;
    
	public String getFileReference() {
		return fileReference;
	}
	public String getLinkURL() {
		return linkURL;
	}
	public String getDescription() {
		return description;
	}
	public String getAlt() {
		return alt;
	}
	public String getDisplay() {
		return display;
	}
    
}
