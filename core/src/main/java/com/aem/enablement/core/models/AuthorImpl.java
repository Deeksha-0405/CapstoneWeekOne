package com.aem.enablement.core.models;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
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

import com.adobe.cq.wcm.core.components.models.Page;
import com.day.cq.search.QueryBuilder;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Author.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuthorImpl implements Author{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Author.class);
	
	@SlingObject
	ResourceResolver resourceResolver;
	
	@Self
	SlingHttpServletRequest slingHttpServletRequest;
	
	@ScriptVariable
	Page currentPage;

	@Inject
	@Via("resource")
	String fname;
	
	@ValueMapValue
	String lname;
	
	@ValueMapValue
	Boolean professor;
	
	@RequestAttribute(name = "rAttribute")
	private String reqAttribute;
	
	@ResourcePath(path = "/content/enablementCapstone/us/en/test")
	@Via("resource")
	Resource resource;
	
	@OSGiService
	QueryBuilder queryBuilder;
	
	@Inject
	@Via("resource")
	@Named("jcr:latModifiedBy")
	String modified;
	

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
		
		return professor;
	}

	@Override
	public String getPageTitle() {
		return currentPage.getTitle();
	}

	@Override
	public String getRequestAttribute() {
		//String result = reqAttribute + "Hello";
		return reqAttribute;
	}

	@Override
	public String getPageName() {
		return resource.getName();
	}
	
	@PostConstruct
	protected void init() {
		
		LOGGER.info("**********************Resource************************************"+resource.getPath());
	}

	@Override
	public String getLastModified() {
		return modified;
	}
	
}
