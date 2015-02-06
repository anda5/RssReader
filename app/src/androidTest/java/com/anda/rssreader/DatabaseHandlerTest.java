package com.anda.rssreader;

import android.test.InstrumentationTestCase;

import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by anda on 1/30/2015.
 */
public class DatabaseHandlerTest extends InstrumentationTestCase{

     private String websiteTitle = "Test title";
     private String websiteDescription = "Description for test";
     private String webSiteLink = "http://abcnews.go.com/";
     private String websiteFeedlink = "http://feeds.abcnews";

     private String newWebSiteTitle="new title";
     private String newWebSiteDescription="new description";
     private String newWebSiteLink="site link";
     private String newWebSiteFeedLink="feed link";

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

    private WebSite createWebSiteObj(){
        return new WebSite(3,websiteTitle,websiteDescription,webSiteLink,websiteFeedlink);
    }

    public void testDatabaseHandlerGet(){
        DatabaseHandler db=new DatabaseHandler(getInstrumentation().getTargetContext());
        db.insertWebSiteObject(createWebSiteObject());
        db.insertRssFeedSiteObject(createRssFeedObject());
        WebSite webSite = db.getWebsiteObject(1);
        RssFeed rssFeed = db.getRssFeedObject(1);
        assertEquals(1,webSite.getId());
        assertEquals(1,rssFeed.getId());
    }

    public void testDatabaseHandlerGetList(){
        DatabaseHandler db = new DatabaseHandler(getInstrumentation().getTargetContext());
        db.insertWebSiteObject(createWebSiteObject());
        db.insertRssFeedSiteObject(createRssFeedObject());
        List<WebSite> webSites = db.getWebsiteList();
        List<RssFeed> rssFeeds = db.getRssFeedList();
        assertFalse(webSites.isEmpty());
        assertFalse(rssFeeds.isEmpty());
    }

    public void testDatabaseHandlerGetWebSiteRssFeedList(){
        DatabaseHandler db = new DatabaseHandler(getInstrumentation().getTargetContext());
        db.insertWebSiteObject(createWebSiteObj());
        db.insertRssFeedSiteObject(createRssFeedObject());
        List<RssFeed> rssFeeds = db.getWebSiteRssFeedList(createWebSiteObj().getId());
        assertEquals(createWebSiteObj().getId(),rssFeeds.iterator().next().getWebSiteId());
    }

    public void testGetCountMethods(){
        DatabaseHandler db = new DatabaseHandler(getInstrumentation().getTargetContext());
        db.insertWebSiteObject(createWebSiteObj());
        db.insertRssFeedSiteObject(createRssFeedObject());
        int webSiteCount=db.getWebSiteCount();
        int feedCount=db.getWebSiteFeedCount(createWebSiteObj().getId());
        assertTrue(webSiteCount>0);
        assertTrue(feedCount>0);
    }

    public void testRemoveWebSIteFeeds(){
       DatabaseHandler db = new DatabaseHandler(getInstrumentation().getTargetContext());
       int id = createWebSiteObj().getId();
       assertEquals(true,db.removeWebsiteFeeds(id));
       assertEquals(0, db.getWebSiteFeedCount(id));
   }
   public void testUpdateWebsiteObject(){
       DatabaseHandler db = new DatabaseHandler(getInstrumentation().getTargetContext());
       WebSite website = createWebSiteObj();
       db.insertWebSiteObject(website);
       WebSite newWebSite=createNewWebSiteObject();
       newWebSite.setId(website.getId());
       boolean result = db.updateWebSiteObject(newWebSite);
       assertEquals(true,result);

   }
   public WebSite createNewWebSiteObject(){
       return new WebSite(newWebSiteTitle,newWebSiteDescription,newWebSiteLink,newWebSiteFeedLink);
   }
}
