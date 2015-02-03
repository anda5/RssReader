package com.anda.rssreader;

import android.test.InstrumentationTestCase;

import java.nio.channels.WritableByteChannel;

/**
 * Created by anda on 1/30/2015.
 */
public class DatabaseHandlerTest extends InstrumentationTestCase{

     private String websiteTitle = "Test title";
     private String websiteDescription = "Description for test";
     private String webSiteLink = "http://abcnews.go.com/";
     private String websiteFeedlink = "http://feeds.abcnews";

     private int websiteId = 3;
     private String feedTitle = "feed title";
     private String feedDescription = "Feed description";
     private String feedLink = "http://rss.realitatea.net/sport.xml";
     private String feedDate = "05.05.2015";
     private String feedURL = "some url for the image";

    public  void  testDatabaseHandlerInsert(){
        DatabaseHandler db=new DatabaseHandler(getInstrumentation().getTargetContext());
        long webId = db.insertWebSiteObject(createWebSiteObject());
        long feedId = db.insertRssFeedSiteObject(createRssFeedObject());
        assertTrue(webId>0);
        assertTrue(feedId>0);
    }

    private RssFeed createRssFeedObject(){
        return new RssFeed(websiteId,feedTitle,feedDescription,feedLink,feedDate,feedURL);
    }

    private WebSite createWebSiteObject() {
        return new WebSite(websiteTitle,websiteDescription,webSiteLink,websiteFeedlink);
    }
}
