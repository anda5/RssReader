package com.anda.rssreader;

import android.test.InstrumentationTestCase;

/**
 * Created by anda on 1/29/2015.
 */
public class RssFeedTest extends InstrumentationTestCase{

    private static final int ID = 5;
    private static final int WEBSITEID = 10;
    private static final String TITLE = "Title Rss-feed";
    private static final String DESCRIPTION = "Description of the feed";
    private static final String FEEDLINK = "http://rss.realitatea.net/sport.xml";
    private static final String DATE = "29.01.2015";
    private static final String IMAGEURL = "http://media.realitatea.net/multimedia/image/201403/w460/frf1_27155300.jpg";

    public void testRssFeedFullConstructor(){
        RssFeed rssFeed = new RssFeed(ID,WEBSITEID,TITLE,DESCRIPTION,FEEDLINK,DATE,IMAGEURL);

        assertEquals(ID,rssFeed.getId());
        assertEquals(WEBSITEID,rssFeed.getWebSiteId());
        assertEquals(TITLE,rssFeed.getTitle());
        assertEquals(DESCRIPTION,rssFeed.getDescription());
        assertEquals(FEEDLINK,rssFeed.getFeedLink());
        assertEquals(DATE,rssFeed.getDate());
        assertEquals(IMAGEURL,rssFeed.getImageURL());
    }

    public void testRssFeedGetterAndSetter(){
        RssFeed rssFeed=new RssFeed();
        rssFeed.setId(ID);
        rssFeed.setWebSiteId(WEBSITEID);
        rssFeed.setTitle(TITLE);
        rssFeed.setDescription(DESCRIPTION);
        rssFeed.setFeedLink(FEEDLINK);
        rssFeed.setDate(DATE);
        rssFeed.setImageURL(IMAGEURL);

        assertEquals(ID,rssFeed.getId());
        assertEquals(WEBSITEID,rssFeed.getWebSiteId());
        assertEquals(TITLE,rssFeed.getTitle());
        assertEquals(DESCRIPTION,rssFeed.getDescription());
        assertEquals(FEEDLINK,rssFeed.getFeedLink());
        assertEquals(DATE,rssFeed.getDate());
        assertEquals(IMAGEURL,rssFeed.getImageURL());
    }
}
