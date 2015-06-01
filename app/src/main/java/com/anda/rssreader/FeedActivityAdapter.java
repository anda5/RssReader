package com.anda.rssreader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anda on 6/1/2015.
 */
public  class FeedActivityAdapter extends RecyclerView.Adapter<FeedActivityAdapter.ViewHolder> {


   private Context context;
   private WebSite webSite;

   public FeedActivityAdapter(Context context,WebSite webSite){
      this.context = context;
      this.webSite = webSite;

   }

   private List<String>  getFeedList (WebSite webSite) {
       List<String> feedList = new ArrayList<>();
       DatabaseHandler db = new DatabaseHandler(context);
       while (db.getWebSiteFeedCount(webSite.getId())>0){
       String feedLink = db.getWebsiteObject(webSite.getId()).getFeedLink();
       feedList.add(feedLink);
       }
       return  feedList;

   }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(getFeedList(webSite).get(position));
    }



    @Override
    public int getItemCount() {
        return getFeedList(webSite).size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener ,View.OnCreateContextMenuListener {

        public TextView itemTitle;


        public ViewHolder(View itemView) {
            super(itemView);
            itemTitle = (TextView) itemView.findViewById(R.id.feedTitle);
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            //TODO: feed intent
            Intent intent = new Intent(context,webSite.getFeedLink().getClass());
            intent.putExtra("ID",getPosition());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);

        }


        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.removeItem(itemTitle.getId());

        }
    }
}
