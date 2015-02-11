package com.anda.rssreader;

import android.test.InstrumentationTestCase;

import java.io.IOException;

/**
 * Created by anda on 2/10/2015.
 */
public class RssParserTest extends InstrumentationTestCase {


     public void testGetRssLinkFromUrl() throws IOException {
         RssParser rsspars = new RssParser();
         String rssfeed = rsspars.getRssLinkFromURL("http://www.gsmarena.com/");
         assertEquals("http://www.gsmarena.com/rss-news-reviews.php3",rssfeed);
     }
    public void testGetXMLfromURL(){
        RssParser rssParser = new RssParser();
        String xml = rssParser.getXMLfromURL("http://www.gsmarena.com/rss-news-reviews.php3");
        assertTrue(xml.startsWith("<?xml"));
    }

}
