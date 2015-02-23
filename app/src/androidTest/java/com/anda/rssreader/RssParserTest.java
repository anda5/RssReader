package com.anda.rssreader;

import android.test.InstrumentationTestCase;

import java.io.IOException;

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
        String rss = rssParser.getXMLfromURL("http://www.feedforall.com/sample-feeds.htm");
        assertEquals("http://www.feedforall.com/blog-feed.xml",rssParser);
    }

    public void testGetWebSiteDetails(){
        RssParser rssParser = new RssParser();
        WebSite website =rssParser.getWebSiteDetails("http://www.feedforall.com/sample-feeds.htm");
        String title = website.getTitle();
        String description = website.getDescription();
        assertEquals("An RSS Daily News Feed from FeedForAll - RSS Feed Creation",title);
        assertEquals("RSS is a fascinating technology",description);
    }


}
