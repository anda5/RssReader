package com.anda.rssreader;

/**
 * Created by anda on 1/28/2015.
 */
public class WebSite {

    private int _id;
    private String _title;
    private String _description;
    private String _siteLink;
    private String _feedLink;

    public WebSite(){
    }

    public WebSite(String title, String description, String siteLink, String feedLink){
        this._title = title;
        this._description = description;
        this._siteLink = siteLink;
        this._feedLink = feedLink;
    }

    public WebSite(int id, String title, String description, String siteLink, String feedLink){
        this(title, description, siteLink, feedLink);
        this._id = id;
    }

    public int getId() {
        return _id;
    }
    public String getTitle(){
        return _title;
    }
    public String getDescription(){
        return _description;
    }
    public String getSiteLink(){
        return _siteLink;
    }
    public String getFeedLink(){
        return _feedLink;
    }

    public void setId(int id) {
        this._id = id;
    }
    public void setTitle(String title){
        this._title = title;
    }
    public void setDescription(String description){
        this._description = description;
    }
    public void setSiteLink(String siteLink){
        this._siteLink = siteLink;
    }
    public void setFeedLink(String feedLink){
        this._feedLink = feedLink;
    }
}
