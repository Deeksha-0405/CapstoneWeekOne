package com.aem.enablement.core.models;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = { SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AgendaVideo {

	@Inject
	@Via("resource")
	private String videotitle;
	
	@Inject
	@Via("resource")
	private String videodesc;
	
	@Inject
	@Via("resource")
	private String videolink;
	
	@Inject
	@Via("resource")
	private String btntitle;
	
	@Inject
	@Via("resource")
	private String btnlink;
	
	@Inject
	@Via("resource")
	private String thumbnailimage;

	public String getVideotitle() {
		return videotitle;
	}

	public void setVideotitle(String videotitle) {
		this.videotitle = videotitle;
	}

	public String getVideodesc() {
		return videodesc;
	}

	public void setVideodesc(String videodesc) {
		this.videodesc = videodesc;
	}

	public String getVideolink() {
		return videolink;
	}

	public void setVideolink(String videolink) {
		this.videolink = videolink;
	}

	public String getBtntitle() {
		return btntitle;
	}

	public void setBtntitle(String btntitle) {
		this.btntitle = btntitle;
	}

	public String getBtnlink() {
		return btnlink;
	}

	public void setBtnlink(String btnlink) {
		this.btnlink = btnlink;
	}

	public String getThumbnailimage() {
		return thumbnailimage;
	}

	public void setThumbnailimage(String thumbnailimage) {
		this.thumbnailimage = thumbnailimage;
	}
	
	
	
	
}
