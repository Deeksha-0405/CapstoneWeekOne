package com.aem.enablement.core.models;

import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class HeaderSecondary {

	@Inject
    private String logo;
	
	@Inject
    private String displayLanguages;
	
	
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDisplayLanguages() {
		return displayLanguages;
	}

	public void setDisplayLanguages(String displayLanguages) {
		this.displayLanguages = displayLanguages;
	}

    
	@Inject
    private Resource languagesitem;
    
    public Resource getLanguagesitem() {
		return languagesitem;
	}

	public void setLanguagesitem(Resource languagesitem) {
		this.languagesitem = languagesitem;
	}


}