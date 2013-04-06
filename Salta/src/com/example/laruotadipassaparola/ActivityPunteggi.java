package com.example.laruotadipassaparola;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityPunteggi extends Activity {
	
	String nome ; String responseBody ; String nulla ;
	int punteggio ; int i ; int j ; int k ;
	Punteggio [] elementi ;
	Punteggio elemento_vettore ;
	TextView posto ;
	TextView punt ;
	TextView nome_utente ;
	View visualizzazione ;
	LayoutInflater inflater ;
	JSONObject obj ; JSONObject oggetto ;
	ArrayAdapter<Punteggio> array ;
	ListView lista_punteggio ;
	boolean esci ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity_punteggi);
		
		lista_punteggio = (ListView) findViewById(R.id.listView1);
		
		try {
			array = new ArrayAdapter<Punteggio>(
					this,R.layout.activity_testo,R.id.textView18,creaElementi()){
				
				@Override
				public View getView(int posizione, View convertView, ViewGroup parent){	
					return ritornoArray(posizione,convertView,parent);
				}
				
				public View ritornoArray(int posizione, View convertView, ViewGroup parent){

					elemento_vettore = getItem(posizione);

					inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
					/** L'inflater viene utilizzato da java per trasformare gli oggetti dall'xml
					 *  in java stesso. Chiamiamo un layout ma siccome i layout sono viewGroup ci 
					 *  tornerà alla fine proprio una View. */
			     	
					visualizzazione = inflater.inflate(R.layout.activity_testo, null);
					
					posto = (TextView) visualizzazione.findViewById(R.id.textView18) ;
					nome_utente = (TextView) visualizzazione.findViewById(R.id.textView20) ;
					punt = (TextView) visualizzazione.findViewById(R.id.textView22) ;
					
					posto.setText(elemento_vettore.getPosizione());
					punt.setText(elemento_vettore.getPunteggio() + "");
					nome_utente.setText(elemento_vettore.getNome());

					return visualizzazione;
				}
				
			/** Nell'array adapter sono state inserite le seguenti caratteristiche: 
			 *  (1) Il contesto this ovvero la seguente activity;
			 *  (2) Il riferimento al layout dove prendere l'item che verrà messo nella list view;
			 *  (3) L'id dell'oggetto(textView) che verrà messo negli item della listView;
			 *  (4) L'array di oggetti Punteggio dove inserire gli oggetti presi(textView). */
			
			};
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lista_punteggio.setAdapter(array);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_activity_punteggi, menu);
		return true;
	}
	
	public Punteggio[] creaElementi() throws ClientProtocolException, IOException, JSONException {
		
		elementi = new Punteggio[10];
		
		/** Inizializziamo il vettore Punteggio settando punteggio a 0 e nome a "". */ 
		
		for (i = 0; i < elementi.length; i++) {    /** i arriverà massimo a 9. */
			
			elementi[i] = new Punteggio();	
			elementi[i].setPosizione((i+1) + "° Posto");
			elementi[i].setPunteggio(0);
			elementi[i].setNome("");
		}
		
		esci = true ;
		oggetto = new JSONObject() ;
		oggetto = Top10(1);
		nulla = oggetto.toString() ;
		
		/** Questo primo for è inserito per prendere gli eventuali punteggi e nomi che arrivano
		 *  dal server. La i è necessaria per far scorrere l'ID della tabella best10 del 
		 *  database.La condizione non sarà più verificata se dal server arriverà un oggetto
		 *  vuoto(non c'è più nulla dopo un certo i) ovvero laddove. */
		
		for (i = 2; nulla.length() > 2 ; i++) {
			
			/** Il secondo for è necessario per scorrere tutto l'array. L'if è verificato se 
			 *  nell'array viene trovato un valore punteggio minore o uguale al valore arrivato
			 *  dal server. Il terzo for in pratica cancella l'ultimo valore dell'array e 
			 *  trasla di un item, verso il basso, tutti i record sovrastanti fino al record
			 *  che è arrivato dal server(esso verrà sostituito al record trovato nel secondo
			 *  for). */
			
			for(k = 0 ; k < elementi.length && esci == true; k++){
				if(oggetto.getInt("PUNTEGGIO") >= elementi[k].getPunteggio()){
					for(j = elementi.length-1; j > k; j--){
						elementi[j].setPunteggio(elementi[j-1].getPunteggio()) ;
						elementi[j].setNome(elementi[j-1].getNome()) ;
					}
					elementi[k].setPunteggio(oggetto.getInt("PUNTEGGIO")) ;
					elementi[k].setNome(oggetto.getString("NOME")) ;
					esci = false ; /** Usciamo dal secondo for. */
				}
			}
			
			esci = true ;
			oggetto = new JSONObject() ;
			oggetto = Top10(i);
			nulla = oggetto.toString() ;
		} 
		
		return elementi ;
	}
	
	 public JSONObject Top10(int ID) throws ClientProtocolException, IOException, JSONException{
	    	
	    	responseBody = "" ;
			
			DefaultHttpClient client = new DefaultHttpClient(); /** Client. */
			String url = "http://10.0.2.2:8080/ServerPunteggi" ; /** Url del Server. */
					
			HttpPost richiesta = new HttpPost(url);
			
			ArrayList<NameValuePair> CoppiaNomeValore = new ArrayList<NameValuePair>();
			CoppiaNomeValore.add(new BasicNameValuePair("ID", ID + ""));
			richiesta.setEntity(new UrlEncodedFormEntity(CoppiaNomeValore));
			
			ResponseHandler<String> responseHandler = new BasicResponseHandler() ; 
			responseBody = client.execute(richiesta, responseHandler);
			obj = new JSONObject(responseBody);
			
			Log.i("HTTP JSONObject: ", obj.toString());
			
			client.getConnectionManager().shutdown(); /** Chiudiamo la connessione. */
			
			return obj ;
	    }
}
