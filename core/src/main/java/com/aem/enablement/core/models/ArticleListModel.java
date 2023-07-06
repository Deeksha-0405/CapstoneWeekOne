package com.aem.enablement.core.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.enablement.core.beans.ArticleListDataBean;
//import com.adobe.cq.wcm.core.components.models.Page;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.wcm.api.Page;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleListModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArticleListModel.class);

	@Inject
	private String articlebasepage;

	public String getArticlebasepage() {
		return articlebasepage;
	}

	public void setArticlebasepage(String articlebasepage) {
		this.articlebasepage = articlebasepage;
	}

	@Self
	Resource resource;
	
	List<ArticleListDataBean> articleListDataBeanArray = null;
	
	

	@PostConstruct
	protected void init() {

		ResourceResolver resourceResolver = resource.getResourceResolver();
		Session session = resourceResolver.adaptTo(Session.class);

		QueryBuilder builder = resourceResolver.adaptTo(QueryBuilder.class);

		Map<String, String> predicate = new HashedMap<String, String>();
		predicate.put("path", articlebasepage);
		predicate.put("type", "cq:Page");

		Query query = null;

		try {
			query = builder.createQuery(PredicateGroup.create(predicate), session);
		} catch (Exception e) {
			LOGGER.error("Error in query", e);
		}

		SearchResult searchResult = query.getResult();
		
		articleListDataBeanArray = new ArrayList<ArticleListDataBean>();

		for (Hit hit : searchResult.getHits()) {
			
			ArticleListDataBean articleListDataBean = new ArticleListDataBean();

			String path = null;
			try {
				path = hit.getPath();
				Resource articleResource = resourceResolver.getResource(path);
				Page articlePage = articleResource.adaptTo(Page.class);
				String title = articlePage.getTitle();
				String description = articlePage.getDescription();
				
				articleListDataBean.setPath(path);
				articleListDataBean.setTitle(title);
				articleListDataBean.setDescription(description);
				
				LOGGER.info("*******Title*********" + title);
				LOGGER.info("*******Description*********" + description);
				LOGGER.info("*******Path*********" + path);
				
				articleListDataBeanArray.add(articleListDataBean);

			} catch (RepositoryException e) {
				throw new RuntimeException(e);
			}
			
			
		}

	}

	public List<ArticleListDataBean> getArticleListDataBeanArray() {
		return articleListDataBeanArray;
	}

}
