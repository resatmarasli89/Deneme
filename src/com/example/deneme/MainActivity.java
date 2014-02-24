package com.example.deneme;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Toast.makeText(MainActivity.this, "Hello World", Toast.LENGTH_SHORT)
				.show();

		Toast.makeText(MainActivity.this, "Bir toast da benden",
				Toast.LENGTH_SHORT).show();

	}

}
