package com.anda.rssreader;

/**
 * Created by anda on 1/29/2015.
 */
public class RssFeed {

    private int _id;
    private int _webSiteId;
    private String _title;
    private String _description;
    private String _feedLink;
    private String _date;
    private String _imageURL;

    public RssFeed() {
    }

    public RssFeed(int id, int webSiteId, String title, String description, String feedLink, String date, String imageURL) {
        this._id = id;
        this._title = title;
        this._webSiteId = webSiteId;
        this._date = date;
        this._description = description;
        this._feedLink = feedLink;
        this._imageURL = imageURL;
    }

    public RssFeed(int webSiteId, String title, String description, String feedLink, String date, String imageURL) {
        this._webSiteId = webSiteId;
        this._title = title;
        this._description = description;
        this._feedLink = feedLink;
        this._date = date;
        this._imageURL = imageURL;
    }
    
    public int getId(){ return _id; }
    public int getWebSiteId(){ return _webSiteId; }
    public String getTitle(){ return _title; }
    public String getDescription(){ return _description; }
    public String getFeedLink(){return _feedLink; }
    public String getDate(){ return _date; }
    public String getImageURL(){ return _imageURL; }

    public void setId(int id){ this._id = id; }
    public void setWebSiteId(int webSiteId){ this._webSiteId = webSiteId; }
    public void setTitle(String title){ this._title = title; }
    public void setDescription(String description){ this._description = description; }
    public void setFeedLink(String feedLink){ this._feedLink = feedLink; }
    public void setDate(String date){ this._date = date;}
    public void setImageURL(String imageURL){ this._imageURL = imageURL; }

}
