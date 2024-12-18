package com.aem.prepare.core.models.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.Session;

import org.apache.lucene.queryparser.xml.builders.FuzzyLikeThisQueryBuilder;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.prepare.core.models.Article;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;


@Model(adaptables = Resource.class,
adapters = Article.class,
		defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleList implements Article{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleList.class);
	
	@Inject
	private String path;

	@Self
	Resource resource;
	public String getPath() {
		return path;
	}
	
	@PostConstruct
	protected void init() {
		
		ResourceResolver resolver = resource.getResourceResolver();
		Session session = resolver.adaptTo(Session.class);
		QueryBuilder builder = resolver.adaptTo(QueryBuilder.class);
		
		Map<String, String> predicate = new HashMap<>();
		predicate.put("path", path);
		predicate.put("type", "cq:Page");
		
		Query query = null;
		try {
			query = builder.createQuery(PredicateGroup.create(predicate),session);
		} catch (Exception e) {
			LOGGER.debug("Error in query", e.getMessage());
		}
		
	}
}