package com.example.ornella.appmeteo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.widget.TextView;
import java.util.ArrayList;


public class MyListView extends Activity {

    private TextView localita;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        localita = (TextView) findViewById(R.id.location);

        String[] nameproducts = new String[]{"Florence", "NewYork", "Melbourne", "Tokyo", "Johannesburg"};

        final ArrayList<String> listp = new ArrayList<String>();
        for (int i = 0; i < nameproducts.length; ++i) {
            listp.add(nameproducts[i]);
        }

        final ListView mylist = (ListView) findViewById(R.id.listView);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listp);
        mylist.setAdapter(adapter);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener()

        {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos,
                                    long id) {

          String titoloriga = (String) adattatore.getItemAtPosition(pos);

          Intent nuovaPagina = new Intent(MyListView.this, MyActivity.class);


          nuovaPagina.putExtra("dato1", titoloriga);
          MyListView.this.startActivity(nuovaPagina);

          nuovaPagina.putExtra("dato1", titoloriga);
            }


        });

    }

}