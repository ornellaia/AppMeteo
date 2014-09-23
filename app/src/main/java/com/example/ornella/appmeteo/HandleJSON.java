package com.example.ornella.appmeteo;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;
import org.json.JSONArray;
import android.util.Log;

public class HandleJSON {
   private String temperature = "temperature";
   private String location = "location";
   private String date = "date";
   private String urlString = null;
   private String image = "image";
   private Runnable runnable_job = null;

   public volatile boolean parsingComplete = true;
   public HandleJSON(String url){
      this.urlString = url;
   }
   public String getTemperatura(){
      return temperature;
   }
   public String getDate() {
        return date;
    }
   public String getWeatherIconUrl() { return image; }



   public void readAndParseJSON(String in) {
      try {
         JSONObject reader = new JSONObject(in);
         JSONObject data = reader.getJSONObject("data");
         JSONArray current_condition = data.getJSONArray("current_condition");
         JSONArray weather = data.getJSONArray("weather");
         JSONArray url_image = weather.getJSONObject(0).getJSONArray("weatherIconUrl");

         image = url_image.getJSONObject(0).getString("value");
         temperature = current_condition.getJSONObject(0).getString("temp_C");
         date = weather.getJSONObject(0).getString("date");

         Log.d("HandleJSON", "Url image: " + image);

         SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
         Date datab = f.parse(date);
         SimpleDateFormat nf = new SimpleDateFormat("dd MMMM yyyy");
         date = nf.format(datab);

         parsingComplete = false;

        } catch (Exception e) {
           e.printStackTrace();
        }
   }
   public void fetchJSON(){

      Thread thread = new Thread(new Runnable(){
      @Override
      public void run() {
         try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            InputStream stream = conn.getInputStream();
            String data = convertStreamToString(stream);
            readAndParseJSON(data);
            Log.d("HandleJSON", "data: " + data);
            stream.close();

         } catch (Exception e) {
            e.printStackTrace();
         }

         }
      });

       thread.start();
   }


   static String convertStreamToString(java.io.InputStream is) {
      java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
      return s.hasNext() ? s.next() : "";
   }
}