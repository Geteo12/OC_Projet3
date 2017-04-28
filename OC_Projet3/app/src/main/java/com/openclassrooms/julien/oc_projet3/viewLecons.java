package com.openclassrooms.julien.oc_projet3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class viewLecons extends AppCompatActivity
{
    private lecons lecon = null;

    public viewLecons ()
    {

    }

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lecons);

        lecon = new lecons();
        lecon.execute();


    }
        public void resultat ()
        {
            lecon.
        }
}
