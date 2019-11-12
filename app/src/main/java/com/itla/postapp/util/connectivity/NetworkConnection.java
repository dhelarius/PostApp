package com.itla.postapp.util.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkConnection {

    private Context context;
    private static NetworkConnection INSTANCE;

    private NetworkConnection(Context context){
        this.context = context;
    }

    public synchronized static NetworkConnection getInstance(Context context){

        if(INSTANCE == null){
            INSTANCE = new NetworkConnection(context);
        }

        return INSTANCE;
    }

    public boolean isConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                              activeNetwork.isConnected();

        return isConnected;
    }
}
