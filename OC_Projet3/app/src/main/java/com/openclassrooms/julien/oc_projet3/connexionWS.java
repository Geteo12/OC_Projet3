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

    /*public String connect ()
    {
        // Parse les données JSON
        try{
            JSONArray jArray = new JSONArray(result);
            for(int i=0;i<jArray.length();i++){
                JSONObject json_data = jArray.getJSONObject(i);
                );
                // Résultats de la requête
                returnString += "\n\t" + jArray.getJSONObject(i);
            }
        }
        catch(JSONException e)
        {
            Log.e("log_tag", "Error parsing data " + e.toString());
        }
    }*/

    static String convertStreamToString(java.io.InputStream is)
    {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
