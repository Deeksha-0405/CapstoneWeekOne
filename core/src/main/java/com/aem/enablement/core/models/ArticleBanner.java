package com.aem.enablement.core.models;


import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import java.util.Calendar;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class ArticleBanner {

		@ValueMapValue
		private String bannerText;
		
		@ValueMapValue
		private String bannerImage;

		public String getBannerText() {
			return bannerText;
		}

		public void setBannerText(String bannerText) {
			this.bannerText = bannerText;
		}

		public String getBannerImage() {
			return bannerImage;
		}

		public void setBannerImage(String bannerImage) {
			this.bannerImage = bannerImage;
		}
		
		@ValueMapValue
		private Calendar lastModified;
		
		@ValueMapValue
		private String path;
		
		public String getPath() {
			return this.path;
		}

		public void setPath(final String path) {
			this.path = path;
		}

		public Calendar getLastModified() {
			return this.lastModified;
		}

		public void setLastModified(final Calendar lastModified) {
			this.lastModified = lastModified;
		}

		
		
		
}
