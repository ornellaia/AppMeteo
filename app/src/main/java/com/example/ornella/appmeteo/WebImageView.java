package com.example.ornella.appmeteo;

import java.io.InputStream;
import java.net.URL;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

public class WebImageView extends ImageView {

public WebImageView(Context context, AttributeSet attrs, int defStyle) {
super(context, attrs, defStyle);
}

public WebImageView(Context context, AttributeSet attrs) {
super(context, attrs);
}

public WebImageView(Context context) {
super(context);
}

private DownloadImageTask task;

public void setUrl(String url) {
synchronized (this) {
if (task != null)
task.cancel(true);

task = (new DownloadImageTask());
task.execute(url);
}
}

private class DownloadImageTask extends
AsyncTask<String, Integer, Drawable> {
private Drawable loadImageFromNetwork(String url) {
try {
InputStream is = (InputStream) new URL(url).getContent();
Drawable d = Drawable.createFromStream(is, "src name");
return d;
} catch (Exception e) {
System.out.println("Exc=" + e);
return null;
}
}

protected Drawable doInBackground(String... urls) {
return loadImageFromNetwork(urls[0]);
}

protected void onPostExecute(Drawable result) {
setImageDrawable(result);
}
}

}