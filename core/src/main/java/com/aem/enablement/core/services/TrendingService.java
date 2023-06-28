package com.aem.enablement.core.services;

import java.util.List;

import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;

import com.aem.enablement.core.models.Trending;



public interface TrendingService {
	
public List<Trending> getTrendingData() throws LoginException, RepositoryException;
}
