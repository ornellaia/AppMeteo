package com.example.ornella.appmeteo;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



public class MyActivity extends Activity {

    private String url1 = "http://api.worldweatheronline.com/free/v1/weather.ashx?q=";
    private String url2 = "&format=json&num_of_days=5&key=b3vk9zj6zthhyq8ng92dz7wh";
    private TextView localita, temperatura;
    private String imageUrl;
    private String url;
    private HandleJSON obj;
    private Database databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my);

        databaseHelper = new Database(this);

        localita = (TextView) findViewById(R.id.location);
        temperatura = (TextView) findViewById(R.id.temperature);
        open(localita, databaseHelper);

        Cursor c1 = databaseHelper.getLocation();

        try

        {
            while (c1.moveToNext())
            {

               Log.d("statistiche", c1.getLong(0) + " " + c1.getString(1) + " " + c1.getString(2) + " " + c1.getString(3) );
            }
        }

        finally

        {
            c1.close();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.aggiorna) {

            String aggiorna = localita.getText().toString();
            aggiorna = aggiorna.replaceAll(" ", " ");
            localita.setText(aggiorna);
            open(localita, databaseHelper);
            return true;

        } else if (id == R.id.configurazione) {

            startActivity(new Intent(MyActivity.this, MyListView.class));
            return true;

        } else if (id == R.id.info) {

            startActivity(new Intent(MyActivity.this, Info.class));
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    public void open(View view, Database databaseHelper) {

        url = "Florence";
        Intent intent = getIntent();

        if (intent != null) {
            if (intent.getStringExtra("dato1") != null) {
                url = intent.getStringExtra("dato1");
            }
        }

        String finalUrl = url1 + url + url2;
        Log.d("HandleJSON", "url: " + url);

        obj = new HandleJSON(finalUrl);
        obj.fetchJSON();

        while(obj.parsingComplete);
        temperatura.setText(obj.getTemperatura() + " C°");
        localita.setText(url + ", " + obj.getDate());
        imageUrl = (obj.getWeatherIconUrl());
        WebImageView webImage1 = (WebImageView) findViewById(R.id.immagine_meteo);
        webImage1.setUrl(imageUrl);

        //Database databaseHelper = new Database(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        databaseHelper.insertMeteo( db, url, obj.getDate(),  obj.getTemperatura() + " C°");

    }


}

