package com.anda.rssreader;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.List;

/**
 * Created by anda on 3/23/2015.
 */
public class MainActivityAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<WebSite> webSiteList;

    public MainActivityAdapter(Context context, List<WebSite> websiteList){
        this.context = context;
        this.webSiteList = websiteList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = new View(LayoutInflater.from(context).inflate(R.layout.website_list,parent).getContext());
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        

    }


    @Override
    public int getItemCount() {
        return webSiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

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
