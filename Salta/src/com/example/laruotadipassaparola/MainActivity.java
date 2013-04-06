package com.example.laruotadipassaparola;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@SuppressWarnings("deprecation")
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		GradientDrawable gd = (GradientDrawable) getResources().getDrawable(R.drawable.bottone);
		
		Button bottone_1 = (Button) findViewById(R.id.button1);
		Button bottone_2 = (Button) findViewById(R.id.button2);
		Button bottone_3 = (Button) findViewById(R.id.button3);
		Button bottone_4 = (Button) findViewById(R.id.button4);
		
		
		bottone_1.setBackgroundDrawable(gd);
		bottone_2.setBackgroundDrawable(gd);
		bottone_3.setBackgroundDrawable(gd);
		bottone_4.setBackgroundDrawable(gd);
		
        bottone_1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent inizia_gioco = new Intent(MainActivity.this,IniziaSfida.class);
				startActivity(inizia_gioco);
			
			}
		});
		
		bottone_2.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent punteggi = new Intent(MainActivity.this,ActivityPunteggi.class);
				startActivity(punteggi);
				
			}
		});
		
		bottone_3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Intent multiplayer = new Intent(MainActivity.this,Inizio_multiplayer.class);
				startActivity(multiplayer);
				
			}
		});
		
		bottone_4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent regole_di_gioco = new Intent(MainActivity.this,Regole_di_gioco.class);
				startActivity(regole_di_gioco);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
