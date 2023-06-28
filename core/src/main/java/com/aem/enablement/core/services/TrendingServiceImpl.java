package com.aem.enablement.core.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.RepositoryException;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aem.enablement.core.models.Trending;


@Component(immediate = true, name = "Trending Service Implementation", service = TrendingService.class)
public class TrendingServiceImpl  implements TrendingService{
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TrendingService.class);


			@Reference
			private ResourceResolverFactory resolverFactory;



			String path = "/content/enablementCapstone/us/en/article";
			

			@Override
			public List<Trending> getTrendingData() throws LoginException, RepositoryException {
			ResourceResolver resolver = null;
			Map<String, Object> param = new HashMap<String, Object>();
			param.put(ResourceResolverFactory.SUBSERVICE, "acs-dev-readuser");

			resolver = resolverFactory.getServiceResourceResolver(param);
			Resource childResource = resolver.getResource(path);
			Node childNode = childResource.adaptTo(Node.class);
			NodeIterator nodeiterater = childNode.getNodes();
			LOGGER.info("After NodeIterator =====================>>");

			List<Trending> trendingmodelitemlist = new ArrayList<Trending>();

			while (nodeiterater.hasNext()) {
			Node node = nodeiterater.nextNode();

			if (!"jcr:content".equals(node.getName())) {



			LOGGER.info("After If=====================>>");
			Node articleNode1 = node.getNode("jcr:content").getNode("root").getNode("container")
			.getNode("articlebanner");
			String nodeName = articleNode1.getName().toString();
			LOGGER.info("ArticleNode Name=====================>>" + nodeName);



			PropertyIterator articleProperties = articleNode1.getProperties();
			String bannerImage = "bannerImage";
			String bannerText = "bannerText";
			String jcrcreated = "jcr:created";
			Trending trendingmodelitem = new Trending();
			while (articleProperties.hasNext()) {

			LOGGER.info("Second While=====================>>");
			Property property = (Property) articleProperties.next();
			if (bannerImage.equals(property.getName())) {



			LOGGER.info("Banner Image =====================>>" + property.getName());
			bannerImage = property.getValue().toString();
			trendingmodelitem.setBannerImage(bannerImage);
			LOGGER.info("path Dynamicxxxx =====================>>" + property.getValue().toString());

			}
			if (bannerText.equals(property.getName())) {
			LOGGER.info("Banner Text =====================>>" + property.getName());
			bannerText = property.getValue().toString();
			trendingmodelitem.setBannerText(bannerText);
			LOGGER.info("path Dynamicxxxx =====================>>" + property.getValue().toString());
			}
			if (jcrcreated.equals(property.getName())) {
			LOGGER.info("Date =====================>>" + property.getName());
			jcrcreated = property.getValue().toString();
			trendingmodelitem.setDate(jcrcreated);
			LOGGER.info("path Dynamicxxxx =====================>>" + property.getValue().toString());
			}


			}


			trendingmodelitemlist.add(trendingmodelitem);



			}

			}
			
			Comparator<Trending> reverseComparator = (c1, c2) -> {
				return c2.getDate().compareTo(c1.getDate());
				};
				Collections.sort(trendingmodelitemlist, reverseComparator);
				
			LOGGER.info("List Inside Service=====================>>" + trendingmodelitemlist);

			return trendingmodelitemlist;
			}
}