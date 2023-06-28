package com.aem.enablement.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class SecondaryHeader {
	
	@ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
	 protected String resourceType;

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

