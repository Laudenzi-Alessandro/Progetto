package com.example.laruotadipassaparola;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.Bundle;
import android.app.Activity;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TempoScaduto extends Activity {
	
	int contatore_finale ;
	TextView tv ;
	String nome ; String responseBody ;
	EditText et ;
	Intent inte ;
	Button invio ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tempo_scaduto);
		
		tv = (TextView) findViewById(R.id.textView17) ;
		et = (EditText) findViewById(R.id.editText2) ;
		invio = (Button) findViewById(R.id.button8);
		
		inte = new Intent() ;
		
		contatore_finale = getIntent().getExtras().getInt("cont");
		tv.setText(contatore_finale + "") ;
		
		invio.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					nome = et.getText().toString() ;
					Record(nome, contatore_finale) ;
					inte = new Intent(TempoScaduto.this, MainActivity.class) ;
					startActivity(inte);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_tempo_scaduto, menu);
		return true;
	}
    
    public void Record(String nome, int punteggio) throws ClientProtocolException, IOException{
    	
    	responseBody = "" ;
		
		DefaultHttpClient client = new DefaultHttpClient(); /** Client. */
		String url = "http://10.0.2.2:8080/Inserimento" ; /** Url del Server. */
				
		HttpPost richiesta = new HttpPost(url);
		
        /** NameValuePairs è un oggetto che rappresenta una coppia nome-valore. */
		ArrayList<NameValuePair> CoppiaNomeValore = new ArrayList<NameValuePair>();
		CoppiaNomeValore.add(new BasicNameValuePair("NOME", nome));
		CoppiaNomeValore.add(new BasicNameValuePair("PUNTEGGIO", punteggio + ""));
		richiesta.setEntity(new UrlEncodedFormEntity(CoppiaNomeValore)); 
		
		Log.i("HTTP nome: ", nome);
		Log.i("HTTP Punteggio:", punteggio + "");
		
		client.execute(richiesta) ;
		client.getConnectionManager().shutdown(); /** Chiudiamo la connessione. */
    }

}
