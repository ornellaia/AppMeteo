package com.example.ornella.appmeteo;

import android.provider.BaseColumns;


public interface  TemperaturaTable extends BaseColumns {

    String TABLE_NAME = "statistiche";

	String LOCATION = "location";

	String TEMPERATURE = "temperature";

    String DATE = "date";

	String[] COLUMNS = new String[]
	{ _ID, LOCATION, DATE , TEMPERATURE };

}
