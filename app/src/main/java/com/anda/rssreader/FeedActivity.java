package com.anda.rssreader;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class FeedActivity extends Activity {
    DatabaseHandler db ;
    Intent intent;
    WebSite webSite;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView.LayoutManager layoutManager = new RecyclerView.LayoutManager() {
            @Override
            public RecyclerView.LayoutParams generateDefaultLayoutParams() {
               recyclerView.findViewById(webSite.getId());
               recyclerView.setHasFixedSize(true);
               recyclerView.setLayoutManager(this);
                return  null;

            }
        };
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        db = new DatabaseHandler(getApplicationContext());
        intent = getIntent();
        if(intent.hasExtra("ID")){
        webSite = db.getWebsiteObject(intent.getExtras().getInt("ID"));
        }else
        {
            Toast.makeText(this,"There is no website info",Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feed, menu);
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
