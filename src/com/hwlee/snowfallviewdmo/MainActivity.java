package com.hwlee.snowfallviewdmo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SnowFallView v = (SnowFallView)findViewById(R.id.snowWindow);
		v.changeState();
		v.setSnowFlake(getApplicationContext().getResources().getDrawable(R.drawable.snowflake02));
	}
	
	public class DownloadSnowFlakeTask extends AsyncTask<String, Void, Integer> {

		@Override
		protected Integer doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		protected void onPostExecute(Integer result) {
	    }
		
	}
	
}
