package com.example.laruotadipassaparola;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Regole_di_gioco extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regole_di_gioco);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_regole_di_gioco, menu);
		return true;
	}

}
