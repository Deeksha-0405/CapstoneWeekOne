package com.aem.enablement.core.models;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Required;
import org.apache.sling.models.annotations.injectorspecific.*;



import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;




@Model(adaptables = {
Resource.class,
SlingHttpServletRequest.class
}, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomeBanner {



@ScriptVariable
private ValueMap properties;



private static final String RES_TYPE = "enablementCapstone/components/articlebanner";



List <ArticleBanner> articlelist = new ArrayList <ArticleBanner>();



@Inject
@Required
@SlingObject
private ResourceResolver resourceResolver;



@Inject
private PageManager pageManager;



public java.util.List < Page > getPages() {



@SuppressWarnings("resource")
Stream < Page > itemStream;




itemStream = getStaticListItems();

return itemStream.collect(Collectors.toList());

}



public Stream < Page > getStaticListItems() {
Stream < Page > getStaticListItems = Arrays.stream(this.properties.get("articleLinks", new String[0]))
.map(pageManager::getContainingPage).filter(Objects::nonNull);
return getStaticListItems;
}



@PostConstruct
public void postConstruct() throws Exception {
pageManager = resourceResolver.adaptTo(PageManager.class);
articlelist = new ArrayList <ArticleBanner>();
List < Page > listItems = getPages();
listItems.forEach(item -> {
Resource contentResource = item.getContentResource();
getChildResource(contentResource, item.getPath());
});



}



public void getChildResource(Resource contentResource, String path) {



Iterator < Resource > childResourceIterator = contentResource.listChildren();
while (null != childResourceIterator && childResourceIterator.hasNext()) {
Resource childResource = childResourceIterator.next();
if (childResource.getResourceType().equals(RES_TYPE)) {
ArticleBanner articlecard = childResource.adaptTo(ArticleBanner.class);
if (path != null) {
articlecard.setPath(path);
}
Page Page = pageManager.getPage(path);
Calendar lastModified = Page.getLastModified();
if (lastModified != null) {
articlecard.setLastModified(lastModified);
}
articlelist.add(articlecard);



break;
} else {
if (childResource.hasChildren())
getChildResource(childResource, path);
}
}
}



public List < ArticleBanner > getArticlelist() {
return articlelist;
}
}