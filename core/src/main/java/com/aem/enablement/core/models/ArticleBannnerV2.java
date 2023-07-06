package com.aem.enablement.core.models;

import java.util.Map;
import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.adobe.cq.wcm.core.components.models.Page;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleBannnerV2 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleBannnerV2.class);
	
	@Inject
	@Via("resource")
	private String articlebannertext;
	
	@Inject
	@Via("resource")
	private String articleroot;
	
	public String getArticlebannertext() {
		return articlebannertext;
	}

	public void setArticlebannertext(String articlebannertext) {
		this.articlebannertext = articlebannertext;
	}

	public String getArticleroot() {
		return articleroot;
	}

	public void setArticleroot(String articleroot) {
		this.articleroot = articleroot;
	}

	@Self
	Resource resource;
	
	@PostConstruct
	protected void init() {
		ResourceResolver resourceResolver = resource.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);
		QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);
		
		Map<String, String> predicate = new HashedMap<>();
		predicate.put("path", articleroot);
		predicate.put("type", "cq:Page");
		
		Query query = null;
		
		try {
			query = builder.createQuery(PredicateGroup.create(predicate), session);
		} catch (Exception e) {
			LOGGER.error("Error in Query", e);
			
		}
		
		SearchResult searchResult = query.getResult();
		
		for(Hit hit : searchResult.getHits()) {
			
			String path = null;
			
			try {
				path = hit.getPath();
				Resource articleResource = resourceResolver.getResource(path);
				Page articlePage = articleResource.adaptTo(Page.class);
				String title = articlePage.getTitle();
				//String description = articlePage.getDescription();
				
				LOGGER.debug("Path:{} \n Title:{}", path, title);
			} catch (RepositoryException e) {
				LOGGER.error("RepositoryException", e);
			}
			
		}
	}
}
