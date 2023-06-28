package com.aem.enablement.core.models;

import static org.apache.sling.api.resource.ResourceResolver.PROPERTY_RESOURCE_TYPE;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class Youtubelinks {
	
	@ValueMapValue(name=PROPERTY_RESOURCE_TYPE, injectionStrategy=InjectionStrategy.OPTIONAL)
    @Default(values="No resourceType")
	 protected String resourceType;
	
	 @Inject
	 private Resource youtubeurllist;

	public Resource getYoutubeurllist() {
		return youtubeurllist;
	}

	public void setYoutubeurllist(Resource youtubeurllist) {
		this.youtubeurllist = youtubeurllist;
	}
	
}
