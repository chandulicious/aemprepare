package com.aem.prepare.core.models.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.lucene.queryparser.flexible.messages.Message;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aemds.guide.dor.ConfigPropertiesService;
import com.adobe.cq.social.tally.client.api.Response;
import com.aem.prepare.core.models.Author;
import com.aem.prepare.core.models.AuthorBooks;
import com.aem.prepare.core.models.MultifieldHelper;
import com.aem.prepare.core.models.NestedHelper;
import com.aem.prepare.core.servlets.PrepareResourceTypeServlet;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(
		adaptables = SlingHttpServletRequest.class,
//		adaptables = Resource.class,
		adapters = AuthorBooks.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class AuthorBooksImpl implements AuthorBooks{

	private static final Logger LOG = LoggerFactory.getLogger(AuthorBooksImpl.class);
	@Reference
	private PrepareResourceTypeServlet prepareResourceTypeServlet;
	@SlingObject
	Resource componentResource;
	
	@ScriptVariable
	Page currentPage;
	
	@ValueMapValue
	@Default(values = "AEM")
	private String authorName;
	
	@ValueMapValue
	private List<String> books;

	
	@Override
	public String getAuthorName() {
		LOG.info("author name:{}",authorName);
		return authorName;
	}

	@Override
	public List<String> getAuthorBooks() {
		if(books != null) {
			return new ArrayList<String>(books);
		}
		else {
			return Collections.emptyList();
		}
	}
	


	@Override
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

	@Override
	public List<MultifieldHelper> getBookDetailsWithBean() {
		List<MultifieldHelper> bookDetailsBean = new ArrayList<>();
		try {
			Resource bookDetailBean = componentResource.getChild("bookdetailswithbean");
			if (bookDetailBean!=null) {
				for(Resource bookBean : bookDetailBean.getChildren()) {
					LOG.info("\n PATH BEAN: {}", bookBean.getPath());
					LOG.info("\n BEAN PRO: {}", bookBean.getValueMap().get("bookname",String.class));
					bookDetailsBean.add(new MultifieldHelper(bookBean));
				}
				
			}
		} catch (Exception e) {
			LOG.info("\n ERROR while getting Book Details with BEAN {}",e.getMessage());
		}
		return bookDetailsBean;
	}
@Override
public List<MultifieldHelper> getBookDetailsWithNestedMultifield() {
	List<MultifieldHelper> bookDetailsNested = new ArrayList<>();
	try {
		Resource bookDetailNested = componentResource.getChild("bookdetailswithnestedmultifield");
		if (bookDetailNested!=null) {
			for(Resource bookNested : bookDetailNested.getChildren()) {
				LOG.info("\n PATH BEAN: {}", bookNested.getPath());
				LOG.info("\n BEAN PRO: {}", bookNested.getValueMap().get("bookname",String.class));
				MultifieldHelper multifieldHelper = new MultifieldHelper(bookNested);
				if(bookNested.hasChildren()) {
					List<NestedHelper> bookNestedList = new ArrayList<>();
					Resource nestedResource = bookNested.getChild("bookeditions");
					for(Resource nested : nestedResource.getChildren()) {
						bookNestedList.add(new NestedHelper(nested));
					}
					multifieldHelper.setBookEditions(bookNestedList);
				}
				bookDetailsNested.add(multifieldHelper);
			}
			
		}
	} catch (Exception e) {
		LOG.info("\n ERROR while getting Book Details with Helper {}",e.getMessage());
	}
	
	return bookDetailsNested;
}

	@PostConstruct
	protected void init() {
		try {
			String title = currentPage.getTitle();
			LOG.info(title);
			
			PageManager pageManager = componentResource.adaptTo(PageManager.class);
			Page page = pageManager.getContainingPage(componentResource);
			ValueMap properties = page.getProperties();
			
			LOG.info(properties.get( "cq:lastModifiedBy", String.class));
		}
		catch (Exception e) {
			LOG.info("error: " + e.getMessage());
		}
	}

}
