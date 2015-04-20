package com.anda.rssreader;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WebSiteActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_site);
        final EditText editText = (EditText) findViewById(R.id.webSiteLink);

        Button button = (Button) findViewById(R.id.webSiteButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (!editText.toString().isEmpty()) {
              ProcessWebSite processWebSite = new ProcessWebSite();
              processWebSite.execute(editText.getText().toString());
                } else {
                       Toast.makeText(getApplicationContext(), "Please enter website link", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private class ProcessWebSite extends AsyncTask<String,Void,Void>{


        @Override
        protected Void doInBackground(String... params) {
            try {

                String userLink = params[0];
                RssParser rssParser = new RssParser();
                String  rssLink = rssParser.getRssLinkFromURL(userLink);
                String  rssFeedXML = rssParser.getXMLFromURL(rssLink);
                WebSite webSite = new WebSite();
                webSite.setFeedLink(rssLink);
                webSite.setSiteLink(userLink);
                webSite = rssParser.getWebsiteDetails(webSite,rssFeedXML);
                DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                databaseHandler.insertWebSiteObject(webSite);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "No feeds found", Toast.LENGTH_SHORT).show();
            }
            return null;
        }
        protected void onPostExecute(String msg) {
            setResult(100);
            finish();
        }
    }
}
