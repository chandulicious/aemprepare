package com.aem.prepare.core.models.impl;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.cq.export.json.ComponentExporter;
import com.adobe.cq.export.json.ExporterConstants;
import com.aem.prepare.core.models.CustomCarouselModel;
import com.aem.prepare.core.models.ImageModel;

@Model(adaptables = SlingHttpServletRequest.class,
		adapters = {CustomCarouselModel.class, ComponentExporter.class},
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

@Exporter(name = ExporterConstants.SLING_MODEL_EXPORTER_NAME,
		extensions = ExporterConstants.SLING_MODEL_EXTENSION)

public class CustomCarouselModelImpl implements CustomCarouselModel, ComponentExporter{

	static final String RESOURCE_TYPE = "prepare/components/content/custom-carousel";
	
	@ValueMapValue
    private String title;

    @ChildResource
    private List<ImageModel> assets;
	
	@Override
	public String getExportedType() {
		return CustomCarouselModelImpl.RESOURCE_TYPE;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ImageModel> getAssets() {
		// TODO Auto-generated method stub
		return null;
	}

}
