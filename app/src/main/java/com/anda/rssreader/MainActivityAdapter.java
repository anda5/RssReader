package com.anda.rssreader;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;

/**
 * Created by anda on 3/23/2015.
 */
public  class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private Context context;
    private List<WebSite> webSiteList;

    public MainActivityAdapter(Context context, List<WebSite> websiteList){
        this.context = context;
        this.webSiteList = websiteList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.website_list,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(webSiteList.get(position).getTitle());
        holder.itemLink.setText(webSiteList.get(position).getSiteLink());
    }
    @Override
    public int getItemCount() {
        return webSiteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

         public TextView itemTitle;
         public TextView itemLink;

         public ViewHolder(View itemView) {
             super(itemView);
             itemTitle = (TextView) itemView.findViewById(R.id.webItemTitle);
             itemLink = (TextView) itemView.findViewById(R.id.webItemLink);
             itemView.setOnClickListener(this);
         }

         @Override
         public void onClick(View v) {
            //TODO: feed intent
         }
    }
}
