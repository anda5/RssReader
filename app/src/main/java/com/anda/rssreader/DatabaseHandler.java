package com.anda.rssreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by anda on 1/30/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

     private static final String DATABASE_NAME = "feedDB";
     private static final int DATABASE_VERSION = 1;
     private static final String TABLE_NAME_WEBSITE = "website";
     private static final String TABLE_NAME_FEEDS = "feed";
     private static final String KEY_WEBSITE_ID = "wid";
     private static final String KEY_WEBSITE_TITLE = "wtitle";
     private static final String KEY_WEBSITE_DESCRIPTION = "wdesc";
     private static final String KEY_WEBSITE_SITE_LINK = "wslink";
     private static final String KEY_WEBSITE_FEED_LINK = "wflink";

     private static final String KEY_FEED_ID = "fid";
     private static final String KEY_FEED_WEBSITE_ID = "fwid";
     private static final String KEY_FEED_TITLE = "ftitle";
     private static final String KEY_FEED_DESCRIPTION = "fdesc";
     private static final String KEY_FEED_FEED_LINK = "flink";
     private static final String KEY_FEED_DATE = "fdate";
     private static final String KEY_FEED_IMAGE_URL = "fimg";

    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String websiteQuery = "CREATE TABLE "+TABLE_NAME_WEBSITE+"("+
                   KEY_WEBSITE_ID +" INTEGER PRIMARY KEY ,"+
                   KEY_WEBSITE_TITLE+" TEXT ,"+
                   KEY_WEBSITE_DESCRIPTION+" TEXT ,"+
                   KEY_WEBSITE_SITE_LINK+ " TEXT ,"+
                   KEY_WEBSITE_FEED_LINK+ " TEXT )";
        db.execSQL(websiteQuery);
      
        String feedQuery = "CREATE TABLE "+TABLE_NAME_FEEDS+"("+
                      KEY_FEED_ID+" INTEGER PRIMARY KEY ,"+
                      KEY_FEED_WEBSITE_ID+" INTEGER, "+
                      KEY_FEED_TITLE+" TEXT ,"+
                      KEY_FEED_DESCRIPTION+" TEXT ,"+
                      KEY_FEED_FEED_LINK+" TEXT ,"+
                      KEY_FEED_DATE+" TEXT ,"+
                      KEY_FEED_IMAGE_URL+" TEXT)";
        db.execSQL(feedQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME_FEEDS);
        db.execSQL("DROP TABLE IF EXIST"+TABLE_NAME_WEBSITE);
        onCreate(db);
    }

    public long insertWebSiteObject(WebSite webSite){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues  cv = createWebSiteContentValues(webSite);
        return db.insert(TABLE_NAME_WEBSITE,null,cv);
    }

    public long insertRssFeedSiteObject(RssFeed rssfeed){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = createRssFeedValues(rssfeed);
        return db.insert(TABLE_NAME_FEEDS,null,cv);
    }

    private ContentValues createRssFeedValues(RssFeed rssfeed) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_FEED_TITLE,rssfeed.getTitle());
        cv.put(KEY_FEED_DESCRIPTION,rssfeed.getDescription());
        cv.put(KEY_FEED_WEBSITE_ID,rssfeed.getWebSiteId());
        cv.put(KEY_FEED_FEED_LINK,rssfeed.getFeedLink());
        cv.put(KEY_FEED_DATE,rssfeed.getDate());
        cv.put(KEY_FEED_IMAGE_URL,rssfeed.getImageURL());
        return cv;
    }

    public ContentValues createWebSiteContentValues(WebSite website) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_WEBSITE_TITLE,website.getTitle());
        cv.put(KEY_WEBSITE_DESCRIPTION,website.getDescription());
        cv.put(KEY_WEBSITE_SITE_LINK,website.getSiteLink());
        cv.put(KEY_WEBSITE_FEED_LINK,website.getFeedLink());
        return cv;
    }
    public WebSite getWebsiteObject(int id ){

        String selectQuery = "select * from "+TABLE_NAME_WEBSITE+" where "+KEY_WEBSITE_ID +"="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try {
            if (cursor.moveToFirst()) {
                WebSite website = new WebSite(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4));
                return website;
            }

        }catch(SQLiteException e){
            Log.d("SQL error", e.getMessage());
            return null;
        }
        return null;
    }
    public RssFeed getRssFeedObject(int id ){

        String selectQuery = "select * from "+TABLE_NAME_FEEDS+" where "+KEY_FEED_ID+"="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        try{
            if(cursor.moveToFirst()){
                RssFeed rssFeed = new RssFeed(Integer.parseInt(cursor.getString(0)),
                        Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6));
                return rssFeed;
            }
        }catch(SQLiteException e) {
            Log.d("SQL error", e.getMessage());
            return null;
        }
        return null;
    }

    public List<WebSite>  getWebsiteList(){
        List<WebSite> webSiteList=new ArrayList<>();
        String query = "select * from "+TABLE_NAME_WEBSITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                WebSite webSite = new WebSite(Integer.parseInt(cursor.getString(0)),
                                                               cursor.getString(1),
                                                               cursor.getString(2),
                                                               cursor.getString(3),
                                                               cursor.getString(4));

                webSiteList.add(webSite);
            }while(cursor.moveToNext());
        return  webSiteList;
        }
        return  null;
    }

    public List<RssFeed> getRssFeedList(){
        List<RssFeed> feedlist = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME_FEEDS;
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
             RssFeed rssfeed = new RssFeed(Integer.parseInt(cursor.getString(0)),
                                           Integer.parseInt(cursor.getString(1)),
                                           cursor.getString(2),
                                           cursor.getString(3),
                                           cursor.getString(4),
                                           cursor.getString(5),
                                           cursor.getString(6));
             feedlist.add(rssfeed);
            }while(cursor.moveToNext());
        return  feedlist;
        }
        return null;
    }

    public List<RssFeed> getWebSiteRssFeedList(int webSiteId){
        List<RssFeed> rssFeeds = new ArrayList<>();
        String query = "select * from "+TABLE_NAME_FEEDS+" where "+KEY_FEED_WEBSITE_ID+"="+webSiteId;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                RssFeed rssFeed = new RssFeed(Integer.parseInt(cursor.getString(0)),
                                              Integer.parseInt(cursor.getString(1)),
                                              cursor.getString(2),
                                              cursor.getString(3),
                                              cursor.getString(4),
                                              cursor.getString(5),
                                              cursor.getString(6));
                rssFeeds.add(rssFeed);
            }while(cursor.moveToNext());
        return rssFeeds;
        }
        return null;
    }
    public int getWebSiteCount() {
        String query = "select * from "+TABLE_NAME_WEBSITE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        return cursor.getCount();
    }

    public int getWebSiteFeedCount(int websiteId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME_FEEDS+" where "+KEY_FEED_WEBSITE_ID+"="+websiteId;
        Cursor cursor = db.rawQuery(query,null);
        return  cursor.getCount();
    }

    public boolean removeWebsiteFeeds(int websiteId){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "delete from "+TABLE_NAME_FEEDS+" where "+KEY_FEED_WEBSITE_ID+"="+websiteId;
        db.execSQL(query);
        if(getWebSiteFeedCount(websiteId)==0){
            return true;
        }else{
            return false;
        }
    }

    public boolean updateWebSiteObject(WebSite website){
        SQLiteDatabase db = this.getWritableDatabase();
        long i = db.update(TABLE_NAME_WEBSITE,createWebSiteContentValues(website),null,null);
        if(i>0){
            return true;
        }
        else{
            return  false;
        }
    }

    public boolean deleteWebsiteObject(WebSite website){
       SQLiteDatabase db = this.getWritableDatabase();
       int count = getWebSiteFeedCount(website.getId());
       long websiterows = db.delete(TABLE_NAME_WEBSITE,KEY_WEBSITE_ID+"="+website.getId(),null);
        if(websiterows>0) {
            if (count > 0) {
                db.delete(TABLE_NAME_FEEDS, KEY_FEED_WEBSITE_ID + "=" + website.getId(), null);
                return true;
            } else {
                return true;
            }
        }
      return false;
    }

}
