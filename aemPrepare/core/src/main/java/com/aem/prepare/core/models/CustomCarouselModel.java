package com.aem.prepare.core.models;
import java.util.List;

import com.adobe.cq.export.json.ComponentExporter;

public interface CustomCarouselModel extends ComponentExporter{
	String getTitle();
    List<ImageModel> getAssets();
}
