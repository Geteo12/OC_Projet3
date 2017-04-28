package com.openclassrooms.julien.oc_projet3;

import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * Created by Julien on 28/04/2017.
 */

public class connexionWS
{
    public static String callJson(/*Context context,*/ String urlstring)
    {

        String data=null;

        try {
            URL url = new URL(urlstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();

            data = convertStreamToString(stream);


            stream.close();

        }catch(SocketTimeoutException e){

            Log.e("SocketTimeOut", "erreur de connexion");
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return data;

    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
