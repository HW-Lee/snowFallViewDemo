package com.hwlee.snowfallviewdmo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new DownloadSnowFlakeTask().execute("http://www.iconpng.com/download/png/44384");
		// http://www.iconpng.com/download/png/66500
		// http://www.iconpng.com/download/png/65980
		// http://www.iconpng.com/download/png/61328
		// http://www.iconpng.com/download/png/44384
	}
	
	public class DownloadSnowFlakeTask extends AsyncTask<String, Void, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				String path = params[0];
				Bitmap bmp = DownloadImageFromPath(path);
				bitmap2Image(bmp);
				return bmp;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		private Bitmap DownloadImageFromPath(String path) throws Exception {
			InputStream in;
			Bitmap bmp;
			int responseCode = -1;
			URL url = new URL(path);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK) {
                //download 
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                in.close();
                return bmp;
            }
            return null;
		}
		
		private void bitmap2Image(Bitmap bmp) throws Exception {
			String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SnowFallViewDemo";
			File dir = new File(file_path);
			if (!dir.exists()) dir.mkdir();
			File file = new File(dir, "snow_flake.png");
			FileOutputStream fOut = new FileOutputStream(file);

		    bmp.compress(Bitmap.CompressFormat.PNG, 85, fOut);
		    fOut.flush();
		    fOut.close();
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			setContentView(R.layout.activity_main);
			SnowFallView v = (SnowFallView)findViewById(R.id.snowWindow);
			v.changeState();
			v.setSnowFlake(result);
	    }
		
	}
	
}
