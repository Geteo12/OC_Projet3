package com.openclassrooms.julien.oc_projet3;

import android.os.AsyncTask;
import android.util.Log;

public class lecons extends AsyncTask<Object, Object, Void>
{
    String connexion = null;

    public lecons ()
    {

    }

    @Override
    protected Void doInBackground(Object... params)
    {
        try
        {
            connexion = connexionWS.callJson("http://localhost/oc_p3_webservices/webservice.php");
        }
        catch (Exception ex)
        {
            Log.e("LECONS", "Exception DL", ex);
            throw new RuntimeException(ex);
        }

        return null;
    }

    protected void onPostExecute (String result)
    {
        Log.e("LECONS", result);
    }
}
