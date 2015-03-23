package com.anda.rssreader;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class WebSiteActivity extends ActionBarActivity {


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
                try {
                    String userLink = editText.toString();
                    RssParser rssParser = new RssParser();
                    String siteLink = rssParser.getRssLinkFromURL(userLink);
                    String feedLink = rssParser.getXMLFromURL(userLink);
                    WebSite webSite = new WebSite();
                    webSite.setFeedLink(feedLink);
                    webSite.setSiteLink(siteLink);
                    webSite.getId();
                    webSite.getTitle();
                    webSite.getFeedLink();
                    webSite.getDescription();
                    webSite.getSiteLink();
                    DatabaseHandler databaseHandler = new DatabaseHandler(getApplicationContext());
                    databaseHandler.insertWebSiteObject(webSite);
                    setResult(100);
                    finish();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "No feeds found", Toast.LENGTH_SHORT);
                    }
                } else {
                       Toast.makeText(getApplicationContext(), "Please enter website link", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_web_site, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
