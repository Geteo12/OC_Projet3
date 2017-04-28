package com.openclassrooms.julien.oc_projet3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button boutonLecon;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonLecon = ((Button) findViewById(R.id.button_lecon));
        boutonLecon.setOnClickListener(lecon);
    }

    private OnClickListener lecon = new OnClickListener()
    {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, viewLecons.class);
            startActivity(intent);
        }
    };
}
