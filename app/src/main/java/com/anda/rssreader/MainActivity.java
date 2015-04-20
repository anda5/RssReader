package com.anda.rssreader;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;


public class MainActivity extends Activity {
   private   RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
   private   RecyclerView recyclerView;
   private   List<WebSite> webSiteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db= new DatabaseHandler(this);
        webSiteList = db.getWebsiteList();

        recyclerView=(RecyclerView)findViewById(R.id.listsView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        if(webSiteList!=null){
        MainActivityAdapter mainActivityAdapter = new MainActivityAdapter(this,webSiteList);
        recyclerView.setAdapter(mainActivityAdapter);
        recyclerView.setVisibility(View.VISIBLE);
        }else{
        recyclerView.setVisibility(View.GONE);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
