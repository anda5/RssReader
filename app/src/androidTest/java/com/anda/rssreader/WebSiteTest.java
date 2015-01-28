package com.anda.rssreader;

import android.test.InstrumentationTestCase;

/**
 * Created by anda on 1/28/2015.
 */
public class WebSiteTest extends InstrumentationTestCase {
    
    private static final int ID = 1;
    private static final String TITLE = "Test Title";
    private static final String DESCRIPTION = "Site Description";
    private static final String SITE_LINK = "http://abcnews.go.com/Site/page/rss--3520115";
    private static final String FEED_LINK = "http://feeds.abcnews.com/abcnews/topstories";

    public void testWebSiteFullConstructor(){
        WebSite webSite=new WebSite(ID, TITLE, DESCRIPTION, SITE_LINK, FEED_LINK);

        assertEquals(ID, webSite.getId());
        assertEquals(TITLE, webSite.getTitle());
        assertEquals(DESCRIPTION, webSite.getDescription());
        assertEquals(SITE_LINK, webSite.getSiteLink());
        assertEquals(FEED_LINK, webSite.getFeedLink());
    }

    public void testWebSiteGetterAndSetter(){
        WebSite webSite = new WebSite();
        webSite.setId(ID);
        webSite.setTitle(TITLE);
        webSite.setDescription(DESCRIPTION);
        webSite.setSiteLink(SITE_LINK);
        webSite.setFeedLink(FEED_LINK);

        assertEquals(ID, webSite.getId());
        assertEquals(TITLE, webSite.getTitle());
        assertEquals(DESCRIPTION, webSite.getDescription());
        assertEquals(SITE_LINK, webSite.getSiteLink());
        assertEquals(FEED_LINK, webSite.getFeedLink());
    }
}
