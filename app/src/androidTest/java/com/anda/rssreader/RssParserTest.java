package com.anda.rssreader;

import android.test.InstrumentationTestCase;

import java.io.IOException;
import java.util.List;

/**
 * Created by anda on 2/10/2015.
 */
public class RssParserTest extends InstrumentationTestCase {

    public void testGetRssLinkFomURL(){
        RssParser rssParser = new RssParser();
        String rss = rssParser.getRssLinkFromURL("http://www.feedforall.com/sample-feeds.htm");
        assertEquals("http://www.feedforall.com/blog-feed.xml",rss);
    }

    public void testGetXMLFromURL(){
        RssParser rssParser = new RssParser();
        String xml = rssParser.getXMLFromURL("http://www.feedforall.com/blog-feed.xml");
        assertTrue(xml.startsWith("<?xml"));
    }

    public void testWebsiteAndRssFeed () {
        WebSite site = new WebSite();
        site.setSiteLink("http://www.feedforall.com/sample-feeds.htm");
        site.setFeedLink("http://www.feedforall.com/blog-feed.xml");

        RssParser rssParser = new RssParser();
        String xml = rssParser.getXMLFromURL("http://www.feedforall.com/blog-feed.xml");

        site = rssParser.getWebsiteDetails(site, xml);
        site.setId(2);

        // Assert website
        assertEquals(site.getId(), 2);
        assertEquals(site.getTitle(), "An RSS Daily News Feed from FeedForAll - RSS Feed Creation.");
        assertTrue(site.getDescription().startsWith("RSS is a fascinating"));
        assertEquals(site.getSiteLink(), "http://www.feedforall.com/sample-feeds.htm");
        assertEquals(site.getFeedLink(), "http://www.feedforall.com/blog-feed.xml");

        List<RssFeed> feedList = rssParser.getFeedDetails(site, xml);

        // Assert feed
        assertNotNull(feedList);
        assertTrue(feedList.size() > 0);

        for (RssFeed feed : feedList) {
            assertTrue(feed.getWebSiteId() == 2);
            assertFalse(feed.getTitle().isEmpty());
            assertFalse(feed.getDescription().isEmpty());
            assertTrue(feed.getFeedLink().startsWith("http://"));
            assertFalse(feed.getDate().isEmpty());
        }
    }

}
