package com.anda.rssreader;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

import java.util.StringTokenizer;

import static com.anda.rssreader.R.id.webSiteLink;

/**
 * Created by anda on 3/10/2015.
 */
public class Utils {


    public static boolean connectedToNetwork(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean result = networkInfo.isConnected() ? true : false;
        return result;
    }

    public static String fullyQualifiedName(String text){
        if(!text.contains("www.")){text = "www."+text;}
        if(!text.contains("http://")){text = "http://"+text;}
        return  text;
    }

}
