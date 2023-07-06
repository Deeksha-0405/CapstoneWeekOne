package com.aem.enablement.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.enablement.core.services.TrendingService;

@Model(adaptables = { Resource.class, SlingHttpServletRequest.class })

public class TrendingQBModel {
	private static final Logger LOGGER = LoggerFactory.getLogger(TrendingQBModel.class);

	@Inject
	private TrendingService tservice;

	String path = "/content/enablementCapstone/us/en/article";

	List<Trending> trendingmodelitemlist = new ArrayList<Trending>();

	@PostConstruct
	protected void init() throws RepositoryException, LoginException {
		LOGGER.info("path Dynamicxxxx =====================>>");

		trendingmodelitemlist = tservice.getTrendingData();
		LOGGER.info("List Inside model=====================>>" + trendingmodelitemlist);
	}

	public List<Trending> getTrendingmodelitemlist() {
		return trendingmodelitemlist;
	}

	public void setTrendingmodelitemlist(List<Trending> trendingmodelitemlist) {
		this.trendingmodelitemlist = trendingmodelitemlist;
	}

}
