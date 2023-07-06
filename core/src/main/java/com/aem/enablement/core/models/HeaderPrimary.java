package com.aem.enablement.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)


public class HeaderPrimary {
	
	@ValueMapValue
	private String trendingText;
	
	@ValueMapValue
	private String trendingDesc;
		
	public String getTrendingText() {
		return trendingText;
	}

	public void setTrendingText(String trendingText) {
		this.trendingText = trendingText;
	}

	public String getTrendingDesc() {
		return trendingDesc;
	}

	public void setTrendingDesc(String trendingDesc) {
		this.trendingDesc = trendingDesc;
	}

	public String currentDate()
	{
		 Date date = new Date();
	      SimpleDateFormat formatter = new SimpleDateFormat("EEEE, dd MMMM yyyy");
	       String str = formatter.format(date);
	      return str;
	}
	

}