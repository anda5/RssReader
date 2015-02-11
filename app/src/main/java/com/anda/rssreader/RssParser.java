package com.anda.rssreader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.w3c.dom.Document.*;
import org.w3c.dom.Element;
import org.w3c.dom.Element.*;


import java.io.IOException;

/**
 * Created by anda on 2/10/2015.
 */
public class RssParser {

  public RssParser(){
  }

  public String getRssLinkFromURL(String url){


      Document doc = null;
      try {
          doc = Jsoup.connect(url).get();
      } catch (IOException e) {
          e.printStackTrace();
      }
      Elements link = doc.select("link[type=application/rss+xml]");
          Elements linkt = doc.select("link[type=application/atom+xml]");
          if(link.size()>0){
              return link.attr("href").toString();}
          else if(linkt.size()>0) {
              return linkt.attr("href").toString();
          }
          else{
              return null;
          }
  }
    public String getXMLfromURL(String feedUrl){
     try{
        DefaultHttpClient client = new DefaultHttpClient();
        HttpEntity entity = null;
        HttpResponse response = null;
        HttpGet httpGet = new HttpGet(feedUrl);
        response = client.execute(httpGet);
        entity = response.getEntity();
        String  responseString = EntityUtils.toString(entity);
        return responseString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
