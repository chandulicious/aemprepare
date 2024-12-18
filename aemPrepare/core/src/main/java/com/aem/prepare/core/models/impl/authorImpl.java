package com.aem.prepare.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.models.annotations.injectorspecific.ResourcePath;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.aem.prepare.core.models.Author;
import com.day.cq.search.QueryBuilder;
import com.day.cq.wcm.api.Page;

@Model(
		adaptables = SlingHttpServletRequest.class,
//		adaptables = Resource.class,
		adapters = Author.class,
		resourceType = authorImpl.RESOURCE_TYPE,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
@Exporter(name = "jackson" , extensions = "json",selector = "",
options = {
		@ExporterOption(name="SerializationFeature.WRAP_ROOT_VALUE",value = "ture"),
		@ExporterOption(name="MapperFeature.SORT_PROPERTIES_ALPHABETICALLY",value = "ture")
})
@JsonRootName("author-details")
public class authorImpl implements Author {
	static final String RESOURCE_TYPE = "/apps/prepare/components/content/author";
	private static final Logger LOG = LoggerFactory.getLogger(authorImpl.class);
	
	@SlingObject
	Resource componentResource;
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@ValueMapValue
	private List<String> books;
//	Injects the adaptable object itself (if the class of the field matches or is a supertype). If the @Self annotation is present it is tried to adapt the adaptable to the field type.
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@ScriptVariable
	Page currentPage;
	
	//to call any service into the model
	@OSGiService
	QueryBuilder queryBuilder;
	
	@Inject
	@Via("resource")
	@Named("jcr:lastModifiedBy") 
	String modifiedBy;

//	The injected fields are normally the properties of a component (which the content authors normally set via component dialog). 
//	In this case, a property named "propertyName" will be looked up from the Resource (after first adapting it to a ValueMap) and it is injected.
//
//	Avoid @Inject. Value injection happens at runtime. If you use @inject then you are making the framework do extra guesswork. It would be good if we use  injector annotations like @ValueMapValue, @childResource
//	Value Map (name=”valuemap”) Injector
//	Gets a property from a ValueMap by name; If @Via is not set, it will automatically take resource if the adaptable is the SlingHttpServletRequest. If name is not set the name is derived from the method/field name.
	
//	It means if we use @@ValueMapValue it will be done automatically and we have to write less code.
	@ValueMapValue
	@Default(values="Prepare")
	String lname;
	
	@Inject
	@Required
	@Default(values="AEM")
	@Via("resource")
	String fname;
	
	@ValueMapValue
	private List<String> test;
	
	@Inject
	@Via("resource")
	Boolean isProfessor;
	
//	 Injects a request attribute by name. If name is not set the name is derived from the method/field name.
	@RequestAttribute(name = "rAttribute")
	private String reqAttribute;
	
	@ResourcePath(path = "/content/aemPrepare/home")@Via("resource")
	Resource resource;
	
	@Override
	public String getCurrentPage() {
		LOG.info("page: ",currentPage.getProperties(getCurrentPage()));
		return currentPage.getTitle();
	}
	
	@Override
	public String getHomePageName() {
		return resource.getName();
	}
	
	@JsonIgnore
	@Override
	public String getRequestAttribute() {
		return reqAttribute;
	}

	@Override
	public String getFirstName() {
		return fname;
	}

	@Override
	public String getLastName() {
		return lname;
	}

	@Override
	public Boolean getIsProfessor() {
		return isProfessor;
	}
	
	@Override
	public String getModifiedBy() {
		LOG.info("jcr modified by:{} ",modifiedBy);
		
		return modifiedBy;
	}
	
	@JsonProperty(value = "author-name")
	public String authorName() {
		return "AEM Prepare";
	}

	@JsonProperty(value = "map-book-details")
	public List<Map<String, String>> getBookDetailsWithMap() {
		List<Map<String, String>> bookDetailsMaps = new ArrayList<>();
		try {
			Resource bookDetail = componentResource.getChild("bookdetailswithmap");
			if (bookDetail!=null) {
				for(Resource book : bookDetail.getChildren()) {
					Map<String, String> bookMap = new HashMap<>();
					bookMap.put("bookname", book.getValueMap().get("bookname",String.class));
					bookMap.put("booksubject", book.getValueMap().get("booksubject",String.class));
					bookMap.put("publishyear", book.getValueMap().get("publishyear",String.class));
					bookDetailsMaps.add(bookMap);
				}
			}
		} catch (Exception e) {
			LOG.info("\n ERROR while getting Book Details {}",e.getMessage());
		}
		LOG.info("\n SIZE {}",bookDetailsMaps.size());
		return bookDetailsMaps;
	}
	public List<String> getAuthorBooks() {
		if(books != null) {
			return new ArrayList<String>(books);
		}
		else {
			return Collections.emptyList();
		}
	}
	
	@PostConstruct
	protected void init() {
		LOG.info("\nInside INIT {}:{}",currentPage.getTitle(),resource.getPath());
		
	}

}
