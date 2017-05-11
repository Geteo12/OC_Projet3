package com.openclassrooms.julien.oc_projet3;


import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class viewLecons extends AppCompatActivity
{
    private lecons      lecon       = null;
    public  TextView    textLecon   = null;
    public  TextView    textTitreDate;
    public  TextView    date;
    public  TextView    textTitreDateSuivante;
    public  TextView    dateSuivante;
    private Button      boutonTerminer;

    public viewLecons ()
    {

    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecons);

        textLecon               = ((TextView) findViewById(R.id.text_lecon));
        textTitreDate           = ((TextView) findViewById(R.id.textView_titre_date));
        date                    = ((TextView) findViewById(R.id.textView_date));
        textTitreDateSuivante   = ((TextView) findViewById(R.id.textView_titre_prochaine_date));
        dateSuivante            = ((TextView) findViewById(R.id.textView_prochaine_date));
        boutonTerminer          = ((Button) findViewById(R.id.button_termine_video));

        boutonTerminer.setOnClickListener(terminer);

        new lecons().execute("http://192.168.1.37/oc_p3_webservices/webservice.php");

        //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=0vsTGO1GPm0"))); /* renvoie vers le site youtube */

        VideoView videoView =(VideoView)findViewById(R.id.video_lecon);
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri uri=Uri.parse("android.resource://com.android.AndroidVideoPlayer"+R.raw.cours_anglais1);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        Log.e("Text lecon base : ", textLecon.toString());
    }

    public View.OnClickListener terminer = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            /*update id_lecon de l'utilisateur*/
        }
    };

    //AsyncTask

    public class lecons extends AsyncTask<String , Void ,String>
    {
        private  String server_response;
        private  StringBuffer response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput (true);
                urlConnection.setDoOutput (true);
                urlConnection.setUseCaches (false);
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nom", /*MainActivity.CURRENT_USER*/"JulienOC");
               /* OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonParam.toString());*/

                // Send POST output.
                DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream ());
                //printout.writeBytes(URLEncoder.encode(jsonParam.toString(),"UTF-8"));
                printout.writeBytes(jsonParam.toString());
                printout.flush ();
                printout.close ();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                }
                else
                {
                    Log.e("HTTP_KO !!!", server_response);
                }
                //out.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("Response", "" + server_response);
            try
            {
                Log.e("VL", textLecon.toString());
                JSONArray jArray = new JSONArray(response.toString());
                textLecon.setText(jArray.getJSONObject(0).getString("nom").toString());
                date.setText(jArray.getJSONObject(0).getString("date").toString());
                dateSuivante.setText(jArray.getJSONObject(0).getString("date_suivante").toString());
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }


        }

// Converting InputStream to String

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
    }


    public class UpdateLecon extends AsyncTask<String , Void ,String>
    {
        private  String server_response;
        private  StringBuffer response;

        @Override
        protected String doInBackground(String... strings) {

            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoInput (true);
                urlConnection.setDoOutput (true);
                urlConnection.setUseCaches (false);
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                //Create JSONObject here
                JSONObject jsonParam = new JSONObject();
                jsonParam.put("nom", /*MainActivity.CURRENT_USER*/"JulienOC");
               /* OutputStreamWriter out = new   OutputStreamWriter(urlConnection.getOutputStream());
                out.write(jsonParam.toString());*/

                // Send POST output.
                DataOutputStream printout = new DataOutputStream(urlConnection.getOutputStream ());
                //printout.writeBytes(URLEncoder.encode(jsonParam.toString(),"UTF-8"));
                printout.writeBytes(jsonParam.toString());
                printout.flush ();
                printout.close ();

                int responseCode = urlConnection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    server_response = readStream(urlConnection.getInputStream());
                    Log.v("CatalogClient", server_response);
                }
                else
                {
                    Log.e("HTTP_KO !!!", server_response);
                }
                //out.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("Response", "" + server_response);
            try
            {

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }


        }

// Converting InputStream to String

        private String readStream(InputStream in) {
            BufferedReader reader = null;
            response = new StringBuffer();
            try {
                reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return response.toString();
        }
    }
}
