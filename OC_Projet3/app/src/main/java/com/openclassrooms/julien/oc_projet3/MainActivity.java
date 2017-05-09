package com.openclassrooms.julien.oc_projet3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button          boutonValider;
    private TextView        texteIdentifiant;
    private EditText        editIdentifiant;
    private String          identifiant;
    private CreateBDD       DBHelper = new CreateBDD(this);
    private SQLiteDatabase  db;
    private Utilisateur     utilisateur = new Utilisateur ();
    public  Utilisateur     user;

    public static           String           CURRENT_USER;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonValider = ((Button) findViewById(R.id.button_connecter));
        boutonValider.setOnClickListener(connecter);

        texteIdentifiant = ((TextView) findViewById(R.id.text_identifiant));
        editIdentifiant = ((EditText) findViewById(R.id.edit_identifiant));

        db = DBHelper.getWritableDatabase();
    }

    private OnClickListener connecter = new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            identifiant = editIdentifiant.getText().toString();

            if(identifiant.length() > 0)
            {
                stockageUtilisateur ();
            }
            else
            {
                texteIdentifiant.setText("Veuillez saisir votre identifiant");
            }
        }
    };

    private void stockageUtilisateur ()
    {
        user = new Utilisateur(identifiant);

        Cursor c = db.rawQuery("SELECT count(*) from " + CreateBDD.TABLE_UTILISATEUR, null);

        if (c.getCount() > 0)
        {
            db.execSQL("DELETE FROM '" + CreateBDD.TABLE_UTILISATEUR + "'");
        }
        db.execSQL("INSERT INTO " + CreateBDD.TABLE_UTILISATEUR + " ('" + CreateBDD.NOM_UTILISATEUR + "') " +
                "VALUES('" + user.getNomUtilisateur()+ "');");

        CURRENT_USER = user.getNomUtilisateur();

        Intent intent = new Intent(MainActivity.this, Menu.class);
        startActivity(intent);
    }
}
