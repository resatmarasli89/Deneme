package com.example.deneme;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.tabs.SampleClass1;
import com.example.tabs.SampleClass2;
import com.example.tabs.SampleClass3;

public class MainActivity extends TabActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUI();

	}

	public void initUI() {
		Countly.sharedInstance().init(MainActivity.this,
				"https://cloud.count.ly", "53033767172bb08c52038fb6");

		TabHost tabHost = getTabHost();

		// Tab for Photos
		TabSpec first_class = tabHost.newTabSpec("First");
		// setting Title and Icon for the Tab
		first_class.setIndicator("Photos",
				getResources().getDrawable(R.drawable.icons_tab));
		Intent photosIntent = new Intent(this, SampleClass1.class);
		first_class.setContent(photosIntent);

		// Tab for Songs
		TabSpec sec_class = tabHost.newTabSpec("Second");
		sec_class.setIndicator("Songs",
				getResources().getDrawable(R.drawable.icons_tab));
		Intent songsIntent = new Intent(this, SampleClass2.class);
		sec_class.setContent(songsIntent);

		// Tab for Videos
		TabSpec third_class = tabHost.newTabSpec("Third");
		third_class.setIndicator("Videos",
				getResources().getDrawable(R.drawable.icons_tab));
		Intent videosIntent = new Intent(this, SampleClass3.class);
		third_class.setContent(videosIntent);

		// Adding all TabSpec to TabHost
		tabHost.addTab(first_class); // Adding photos tab
		tabHost.addTab(sec_class); // Adding songs tab
		tabHost.addTab(third_class); // Adding videos tab

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Countly.sharedInstance().onStart();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Countly.sharedInstance().onStop();
	}
}
