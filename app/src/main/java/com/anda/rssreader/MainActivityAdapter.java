package com.anda.rssreader;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.List;

/**
 * Created by anda on 3/23/2015.
 */
public class MainActivityAdapter {

    private Context context;
    private List<WebSite> webSiteList;

    public MainActivityAdapter(Context context, List<WebSite> websiteList){
        this.context = context;
        this.webSiteList = websiteList;
    }

     public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         public View itemTitle;
         public View itemLink;

         public ViewHolder(View itemView) {
             super(itemView);
             itemTitle = itemView.findViewById(R.id.webItemTitle);
             itemLink = itemView.findViewById(R.id.webItemLink);
             itemView.setOnClickListener(this);
         }

         @Override
         public void onClick(View v) {
            //TODO: feed intent
         }
     }
}
