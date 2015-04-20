package com.anda.rssreader;

import android.provider.ContactsContract;
import android.provider.DocumentsContract;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.w3c.dom.Document.*;
import org.w3c.dom.Element;
import org.w3c.dom.Element.*;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by anda on 2/10/2015.
 */
public class RssParser {

    public RssParser() {}

    public String getRssLinkFromURL(String url) {
        Document doc = null;
        try {
            doc =  Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements rssLink = doc.select("link[type=application/rss+xml]");
        Elements atomLink = doc.select("link[type=application/atom+xml]");
        if(rssLink.size()>0){
            return rssLink.attr("href");}
        else if(atomLink.size()>0) {
            return atomLink.attr("href");
        }
        else {
            return null;
        }
    }

    public String getXMLFromURL(String feedUrl) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(feedUrl);
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public WebSite getWebsiteDetails (WebSite site, String xml) {
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());
        Elements title = doc.select("channel > title");
        Elements description = doc.select("channel > description");

        for (int i = 0; i < title.size(); i++) {
            site.setTitle(title.get(i).text());
            site.setDescription(description.get(i).text());
        }
        return site;
    }

    public List<RssFeed> getFeedDetails (WebSite site, String xml) {
        List<RssFeed> rssList = new ArrayList<>();
        Document doc = Jsoup.parse(xml, "", Parser.xmlParser());

        Elements title = doc.select("item > title");
        Elements description = doc.select("item > description");
        Elements link = doc.select("item > link");
        Elements date = doc.select("item > pubDate");
        Elements image = doc.select("item > image > url");

        for (int i = 0; i < title.size(); i++) {
            RssFeed feed = new RssFeed();
            feed .setWebSiteId(site.getId());

            feed.setTitle(title.get(i).text());
            feed.setDescription(description.get(i).text());
            feed.setFeedLink(link.get(i).text());
            feed.setDate(date.get(i).text());

            if (image.size() > 0) {
                feed.setImageURL(image.get(i).text());
            } else {
                feed.setImageURL("");
            }
            rssList.add(feed);
        }
        return rssList;
    }

}

