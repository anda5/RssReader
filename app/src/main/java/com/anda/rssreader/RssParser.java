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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by anda on 2/10/2015.
 */
public class RssParser {

    private static final String TAG_WEBSITE_CHANEL = "chanel";
    private static final String TAG_WEBSITE_TITLE = "title";
    private static final String TAG_WEBSITE_DESCRIPTION = "description";
    private static final String TAG_WEBSITE_LINK = "link";
    private static final String TAG_FEED_ITEM = "item";
    private static final String TAG_FEED_TITLE = "title";
    private static final String TAG_FEED_DESCRIPTION = "description";
    private static final String TAG_FEED_LINK = "link";
    private static final String TAG_FEED_DATE = "pubDate";
    private static final String TAG_FEED_IMAGE = "image";

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
    
    public Document getDomElement(String xml)  {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder  documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        InputSource imputSource = new InputSource();
        imputSource.setCharacterStream(new StringReader(xml));
        try {
            return (Document)documentBuilder.parse(imputSource);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getElementValue (Node elem){
        if(elem != null){
            if(elem.hasChildNodes()){
                for(Node child = elem.getFirstChild();child != null;child = child.getNextSibling()){
                    if(child.getNodeType()== Node.TEXT_NODE || child.getNodeType()== Node.CDATA_SECTION_NODE){
                        return child.getNodeValue();}

                }
            }
        }
        else{
            return "";
        }
        return null;
    }
    
    public String getValue(Element item , String str){
        NodeList nodeList = item.getElementsByTagName(str);
        return  getElementValue(nodeList.item(0));

    }


}
