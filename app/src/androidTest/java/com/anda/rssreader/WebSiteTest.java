package com.anda.rssreader;

import android.test.InstrumentationTestCase;

/**
 * Created by anda on 1/28/2015.
 */
public class WebSiteTest extends InstrumentationTestCase {

    private int id=1;
    private String title="Test title";
    private String description="Site description";
    private String siteLink="http://abcnews.go.com/Site/page/rss--3520115";
    private String feedLink="http://feeds.abcnews.com/abcnews/topstories";

    public void testWebSiteFullConstructor(){
        WebSite webSite=new WebSite(id,title,description,siteLink,feedLink);
        assertEquals(id,webSite.getId());
        assertEquals(title,webSite.getTitle());
        assertEquals(description,webSite.getDescription());
        assertEquals(siteLink,webSite.getSiteLink());
        assertEquals(feedLink,webSite.getFeedLink());
    }

    public void testWebSiteGetterAndSetter(){
        WebSite webSite=new WebSite();
        webSite.setId(id);
        webSite.setTitle(title);
        webSite.setDescription(description);
        webSite.setSiteLink(siteLink);
        webSite.setFeedLink(feedLink);
        assertEquals(id,webSite.getId());
        assertEquals(title,webSite.getTitle());
        assertEquals(description,webSite.getDescription());
        assertEquals(siteLink,webSite.getSiteLink());
        assertEquals(feedLink,webSite.getFeedLink());
    }
}
