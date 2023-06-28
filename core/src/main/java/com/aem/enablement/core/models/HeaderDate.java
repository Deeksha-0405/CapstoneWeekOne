package com.aem.enablement.core.models;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = { Resource.class,
		SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class HeaderDate {
	
	public String currentDate()
    {
         Date date = new Date();
          SimpleDateFormat formatter = new SimpleDateFormat("EEEE" +", " + "dd MMMM yyyy");
           String str = formatter.format(date);
          return str;
    }
	

}
