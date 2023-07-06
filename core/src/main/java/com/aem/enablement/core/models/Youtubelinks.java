package com.aem.enablement.core.models;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = { Resource.class}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class YoutubeLinks {
	
	@Inject
	public Resource youtubelinks;

	public Resource getYoutubelinks() {
		return youtubelinks;
	}

	public void setYoutubelinks(Resource youtubelinks) {
		this.youtubelinks = youtubelinks;
	}

	
}
