package com.example.ornella.appmeteo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Info extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);

        TextView t = (TextView) findViewById(R.id.text_about);
        t.setText(getString(R.string.info_text));

        Intent intent = getIntent();
        if (intent != null) {

            String text = "App Developed by\nD-Mobile Lab";
            t.setText(Html.fromHtml(text));

        }

        Button button1 = (Button) findViewById(R.id.button);

		button1.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View arg0)  {
			  Log.d("Info", "button");
              finish(); }


			});


    }

}






