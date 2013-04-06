package com.example.laruotadipassaparola;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

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
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Multiplayer extends Activity {
	
	int contatore_1 ; int secondi ; int differenza ; int i ; int x ; int id ;
	Random ra ;
	String url ; String dbName ; String username ; String password ; String driver ; String sql ;
	String spazio ; String responseBody ;
	Handler ha;
	Intent in ;
	TextView tempo ; TextView lettera_corrente ; TextView domande ; TextView risposte_esatte ;
    ImageView im_A ; ImageView im_B ; ImageView im_C ; ImageView im_D ; ImageView im_E ;
    ImageView im_F ; ImageView im_G ; ImageView im_H ; ImageView im_I ; ImageView im_L ;
    ImageView im_M ; ImageView im_N ; ImageView im_O ; ImageView im_P ; ImageView im_Q ;
    ImageView im_R ; ImageView im_S ; ImageView im_T ; ImageView im_U ; ImageView im_V ; 
    ImageView im_Z ;
    Button bottone_risposta ; Button bottone_salta ; 
    EditText et ;
    Lettera A ; Lettera B ; Lettera C ; Lettera D ; Lettera E ; Lettera F ; Lettera G ;
    Lettera H ; Lettera I ; Lettera L ; Lettera M ; Lettera N ; Lettera O ; Lettera P ;
    Lettera Q ; Lettera r ; Lettera S ; Lettera T ; Lettera U ; Lettera V ; Lettera Z ;
    Lettera [] vettore ;
    JSONObject obj ; JSONObject oggetto ;
    Connection conn ;
	Statement sta ;
	ResultSet rs ; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiplayer);
		
		i = 0 ; x = 0 ; contatore_1 = 0 ;
		A = new Lettera() ;	B = new Lettera() ; C = new Lettera() ; D = new Lettera() ;
		E = new Lettera() ; F = new Lettera() ; G = new Lettera() ; H = new Lettera() ;
		I = new Lettera() ; L = new Lettera() ; M = new Lettera() ; N = new Lettera() ;
		O = new Lettera() ; P = new Lettera() ; Q = new Lettera() ; r = new Lettera() ;
		S = new Lettera() ; T = new Lettera() ; U = new Lettera() ; V = new Lettera() ;
		Z = new Lettera() ;
		vettore = new Lettera[21] ;
		ha  = new Handler();
		oggetto = new JSONObject() ;
		bottone_risposta = (Button) findViewById(R.id.button5);
		bottone_salta = (Button) findViewById(R.id.button6);
		im_A = (ImageView) findViewById(R.id.imageView12);
		im_B = (ImageView) findViewById(R.id.imageView10);
		im_C = (ImageView) findViewById(R.id.imageView11);
		im_D = (ImageView) findViewById(R.id.imageView1);
		im_E = (ImageView) findViewById(R.id.imageView2);
		im_F = (ImageView) findViewById(R.id.imageView3);
		im_G = (ImageView) findViewById(R.id.imageView4);
		im_H = (ImageView) findViewById(R.id.imageView24);
		im_I = (ImageView) findViewById(R.id.imageView23);
		im_L = (ImageView) findViewById(R.id.imageView22);
		im_M = (ImageView) findViewById(R.id.imageView21);
		im_N = (ImageView) findViewById(R.id.imageView20);
		im_O = (ImageView) findViewById(R.id.imageView19);
		im_P = (ImageView) findViewById(R.id.imageView18);
		im_Q = (ImageView) findViewById(R.id.imageView17);
		im_R = (ImageView) findViewById(R.id.imageView16);
		im_S = (ImageView) findViewById(R.id.imageView15);
		im_T = (ImageView) findViewById(R.id.imageView14);
		im_U = (ImageView) findViewById(R.id.imageView13);
		im_V = (ImageView) findViewById(R.id.imageView8);
		im_Z = (ImageView) findViewById(R.id.imageView6);
		domande = (TextView) findViewById(R.id.textView11);
		tempo = (TextView) findViewById(R.id.textView13);
		lettera_corrente = (TextView) findViewById(R.id.textView12);
		risposte_esatte = (TextView) findViewById(R.id.textView14);
	    et = (EditText) findViewById(R.id.editText1);
	    spazio = "            " ;
	    try {
			A.setStato(0) ; A.setLettera("A") ; oggetto = informazioni(A.getLettera()) ;
			A.setDomanda(oggetto.getString("DOMANDA")); 
			A.setRisposta(oggetto.getString("RISPOSTA"));
			
			B.setStato(0) ; B.setLettera("B") ; oggetto = informazioni(B.getLettera()) ;
			B.setDomanda(oggetto.getString("DOMANDA")) ;
			B.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			C.setStato(0) ; C.setLettera("C") ; oggetto = informazioni(C.getLettera()) ;
			C.setDomanda(oggetto.getString("DOMANDA")) ;
			C.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			D.setStato(0) ; D.setLettera("D") ; oggetto = informazioni(D.getLettera()) ;
			D.setDomanda(oggetto.getString("DOMANDA")) ;
			D.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			E.setStato(0) ; E.setLettera("E") ; oggetto = informazioni(E.getLettera()) ;
			E.setDomanda(oggetto.getString("DOMANDA")) ;
			E.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			F.setStato(0) ; F.setLettera("F") ; oggetto = informazioni(F.getLettera()) ;
			F.setDomanda(oggetto.getString("DOMANDA")) ;
			F.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			G.setStato(0) ; G.setLettera("G") ; oggetto = informazioni(G.getLettera()) ;
			G.setDomanda(oggetto.getString("DOMANDA")) ;
			G.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			H.setStato(0) ; H.setLettera("H") ; oggetto = informazioni(H.getLettera()) ;
			H.setDomanda(oggetto.getString("DOMANDA")) ;
			H.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			I.setStato(0) ; I.setLettera("I") ; oggetto = informazioni(I.getLettera()) ;
			I.setDomanda(oggetto.getString("DOMANDA")) ;
			I.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			L.setStato(0) ; L.setLettera("L") ; oggetto = informazioni(L.getLettera()) ;
			L.setDomanda(oggetto.getString("DOMANDA")) ;
			L.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			M.setStato(0) ; M.setLettera("M") ; oggetto = informazioni(M.getLettera()) ;
			M.setDomanda(oggetto.getString("DOMANDA")) ;
			M.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			N.setStato(0) ; N.setLettera("N") ; oggetto = informazioni(N.getLettera()) ;
			N.setDomanda(oggetto.getString("DOMANDA")) ;
			N.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			O.setStato(0) ; O.setLettera("O") ; oggetto = informazioni(O.getLettera()) ;
			O.setDomanda(oggetto.getString("DOMANDA")) ;
			O.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			P.setStato(0) ; P.setLettera("P") ; oggetto = informazioni(P.getLettera()) ;
			P.setDomanda(oggetto.getString("DOMANDA")) ;
			P.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			Q.setStato(0) ; Q.setLettera("Q") ; oggetto = informazioni(Q.getLettera()) ;
			Q.setDomanda(oggetto.getString("DOMANDA")) ;
			Q.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			r.setStato(0) ; r.setLettera("R") ; oggetto = informazioni(r.getLettera()) ;
			r.setDomanda(oggetto.getString("DOMANDA")) ;
			r.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			S.setStato(0) ; S.setLettera("S") ; oggetto = informazioni(S.getLettera()) ;
			S.setDomanda(oggetto.getString("DOMANDA")) ;
			S.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			T.setStato(0) ; T.setLettera("T") ; oggetto = informazioni(T.getLettera()) ;
			T.setDomanda(oggetto.getString("DOMANDA")) ;
			T.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			U.setStato(0) ; U.setLettera("U") ; oggetto = informazioni(U.getLettera()) ;
			U.setDomanda(oggetto.getString("DOMANDA")) ;
			U.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			V.setStato(0) ; V.setLettera("V") ; oggetto = informazioni(V.getLettera()) ;
			V.setDomanda(oggetto.getString("DOMANDA")) ;
			V.setRisposta(oggetto.getString("RISPOSTA")) ;
			
			Z.setStato(0) ; Z.setLettera("Z") ; oggetto = informazioni(Z.getLettera()) ;
			Z.setDomanda(oggetto.getString("DOMANDA")) ;
			Z.setRisposta(oggetto.getString("RISPOSTA")) ;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }	
		
		vettore[0] = A ; vettore[1] = B ; vettore[2] = C ; vettore[3] = D ; vettore[4] = E ;
		vettore[5] = F ; vettore[6] = G ; vettore[7] = H ; vettore[8] = I ; vettore[9] = L ;
		vettore[10] = M ; vettore[11] = N ; vettore[12] = O ; vettore[13] = P ; vettore[14] = Q ;
		vettore[15] = r ; vettore[16] = S ; vettore[17] = T ; vettore[18] = U ; vettore[19] = V ;
		vettore[20] = Z ;
		
		countdown() ;
		
		risposte_esatte.setText(contatore_1 + "");
		lettera_corrente.setText(spazio + vettore[i].getLettera());
		domande.setText(vettore[i].getDomanda());
		
		bottone_risposta.setOnClickListener(new OnClickListener() {
				
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				/** Questo if viene inserito per vedere se la risposta è giusta o no. */
				
				if(vettore[i].getRisposta().equals(et.getText().toString())){
					vettore[i].setStato(1) ;
					stato_domanda(vettore[i].getStato(),i);
					contatore_1++ ;
					MostraStatoContatore();    
				}
					
				else{
					vettore[i].setStato(2) ;
				    stato_domanda(vettore[i].getStato(),i);    
				}
				
				x++;
				i++;
				
				/** Questo ciclo while viene inserito per trovare una lettera, 
				 *  nel vettore, che abbia uno stato uguale a 0(lettera trasparente) o
				 *  uno stato uguale a tre (lettera gialla), successiva alla lettera 
				 *  del vettore con indice i. */
				
				while(i < 21){
				    if(vettore[i].getStato() == 0 || vettore[i].getStato() == 3){
				    	lettera_corrente.setText(spazio + vettore[i].getLettera());
				        domande.setText(vettore[i].getDomanda());
				        break;		    
				    }	
				    i++ ;
				}
				
				if(i >= 21){	
					
					/** Questo ciclo for viene inserito per trovare una lettera, 
					 *  nel vettore, che abbia uno stato uguale a tre (lettera gialla),
					 *  precedente alla lettera del vettore con indice i. */
				    
					for(i = 0 ; i < 21; i++){
				    	if(vettore[i].getStato() == 3){
				    		lettera_corrente.setText(spazio + vettore[i].getLettera());
						    domande.setText(vettore[i].getDomanda());
						    break;				 			    
				    	}	
				    }	    
				}
				
				et.setText(null);	
			}	
		});
		
		bottone_salta.setOnClickListener(new OnClickListener() {
						
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				vettore[i].setStato(3) ;
				stato_domanda(vettore[i].getStato(),i);
				i++;
				
				/** Questo ciclo while viene inserito per trovare una lettera, 
				 *  nel vettore, che abbia uno stato uguale a 0(lettera trasparente) o
				 *  uno stato uguale a tre (lettera gialla), successiva alla lettera 
				 *  del vettore con indice i. */
				
				while(i < 21){
					if(vettore[i].getStato() == 0 || vettore[i].getStato() == 3){
						lettera_corrente.setText(spazio + vettore[i].getLettera());
					    domande.setText(vettore[i].getDomanda());
						break;
					}
					i++ ;
				}
				
				if(i >= 21){		
					
					/** Questo ciclo for viene inserito per trovare una lettera, 
				     *  nel vettore, che abbia uno stato uguale a tre (lettera gialla),
				     *  precedente alla lettera del vettore con indice i. */
					
					for(i = 0 ; i < 21; i++){
						if(vettore[i].getStato() == 3){
							lettera_corrente.setText(spazio + vettore[i].getLettera());
							domande.setText(vettore[i].getDomanda());
							break;				 			 	
						}	 
					}	 	 
				}
				
				et.setText(null);	
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_multiplayer, menu);
		return true;
	}
	
	/** Ora faremo un metodo che 'etichetterà' ogni domanda a seconda se sarà risposta 
	 *  correttamente, in maniera errata o verrà saltata. */
	
	public void stato_domanda(int stato, int lettera){
		if(stato == 1){
			switch(lettera){
			case 0:  im_A.setImageResource(R.drawable.a_verde); break ;
			case 1:  im_B.setImageResource(R.drawable.b_verde); break ;
			case 2:  im_C.setImageResource(R.drawable.c_verde); break ;
			case 3:  im_D.setImageResource(R.drawable.d_verde); break ;
			case 4:  im_E.setImageResource(R.drawable.e_verde); break ;
			case 5:  im_F.setImageResource(R.drawable.f_verde); break ;
			case 6:  im_G.setImageResource(R.drawable.g_verde); break ;
			case 7:  im_H.setImageResource(R.drawable.h_verde); break ;
			case 8:  im_I.setImageResource(R.drawable.i_verde); break ;
			case 9:  im_L.setImageResource(R.drawable.l_verde); break ;
			case 10:  im_M.setImageResource(R.drawable.m_verde); break ;
			case 11:  im_N.setImageResource(R.drawable.n_verde); break ;
			case 12:  im_O.setImageResource(R.drawable.o_verde); break ;
			case 13:  im_P.setImageResource(R.drawable.p_verde); break ;
			case 14:  im_Q.setImageResource(R.drawable.q_verde); break ;
			case 15:  im_R.setImageResource(R.drawable.r_verde); break ;
			case 16:  im_S.setImageResource(R.drawable.s_verde); break ;
			case 17:  im_T.setImageResource(R.drawable.t_verde); break ;
			case 18:  im_U.setImageResource(R.drawable.u_verde); break ;
			case 19:  im_V.setImageResource(R.drawable.v_verde); break ;
			case 20:  im_Z.setImageResource(R.drawable.z_verde); break ;
			}
		}
		else if(stato == 2){
			switch(lettera){
			case 0:  im_A.setImageResource(R.drawable.a_rossa); break ;
			case 1:  im_B.setImageResource(R.drawable.b_rossa); break ;
			case 2:  im_C.setImageResource(R.drawable.c_rossa); break ;
			case 3:  im_D.setImageResource(R.drawable.d_rossa); break ;
			case 4:  im_E.setImageResource(R.drawable.e_rossa); break ;
			case 5:  im_F.setImageResource(R.drawable.f_rossa); break ;
			case 6:  im_G.setImageResource(R.drawable.g_rossa); break ;
			case 7:  im_H.setImageResource(R.drawable.h_rossa); break ;
			case 8:  im_I.setImageResource(R.drawable.i_rossa); break ;
			case 9:  im_L.setImageResource(R.drawable.l_rossa); break ;
			case 10:  im_M.setImageResource(R.drawable.m_rossa); break ;
			case 11:  im_N.setImageResource(R.drawable.n_rossa); break ;
			case 12:  im_O.setImageResource(R.drawable.o_rossa); break ;
			case 13:  im_P.setImageResource(R.drawable.p_rossa); break ;
			case 14:  im_Q.setImageResource(R.drawable.q_rossa); break ;
			case 15:  im_R.setImageResource(R.drawable.r_rossa); break ;
			case 16:  im_S.setImageResource(R.drawable.s_rossa); break ;
			case 17:  im_T.setImageResource(R.drawable.t_rossa); break ;
			case 18:  im_U.setImageResource(R.drawable.u_rossa); break ;
			case 19:  im_V.setImageResource(R.drawable.v_rossa); break ;
			case 20:  im_Z.setImageResource(R.drawable.z_rossa); break ;
			} 
		}
		else if(stato == 3){
			switch(lettera){
			case 0:  im_A.setImageResource(R.drawable.a_gialla); break ;
			case 1:  im_B.setImageResource(R.drawable.b_gialla); break ;
			case 2:  im_C.setImageResource(R.drawable.c_gialla); break ;
			case 3:  im_D.setImageResource(R.drawable.d_gialla); break ;
			case 4:  im_E.setImageResource(R.drawable.e_gialla); break ;
			case 5:  im_F.setImageResource(R.drawable.f_gialla); break ;
			case 6:  im_G.setImageResource(R.drawable.g_gialla); break ;
			case 7:  im_H.setImageResource(R.drawable.h_gialla); break ;
			case 8:  im_I.setImageResource(R.drawable.i_gialla); break ;
			case 9:  im_L.setImageResource(R.drawable.l_gialla); break ;
			case 10:  im_M.setImageResource(R.drawable.m_gialla); break ;
			case 11:  im_N.setImageResource(R.drawable.n_gialla); break ;
			case 12:  im_O.setImageResource(R.drawable.o_gialla); break ;
			case 13:  im_P.setImageResource(R.drawable.p_gialla); break ;
			case 14:  im_Q.setImageResource(R.drawable.q_gialla); break ;
			case 15:  im_R.setImageResource(R.drawable.r_gialla); break ;
			case 16:  im_S.setImageResource(R.drawable.s_gialla); break ;
			case 17:  im_T.setImageResource(R.drawable.t_gialla); break ;
			case 18:  im_U.setImageResource(R.drawable.u_gialla); break ;
			case 19:  im_V.setImageResource(R.drawable.v_gialla); break ;
			case 20:  im_Z.setImageResource(R.drawable.z_gialla); break ;
			}  
		}
	}
	
	/** Qui verrà fatto un metodo che mostretà le domande esatte volta per volta. */
	
	public void MostraStatoContatore() {
		// TODO Auto-generated method stub
		risposte_esatte.setText(contatore_1 + "");
	}
	
	/** Da qui in poi comincia il worker Thread per il countdown. */
	
	public void countdown() {	
		
		secondi = 150 ;
		differenza = 1 ; /** Settiamo con un valore diverso da zero. */
		
		Runnable ru = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			    for(int i = 0 ; differenza != 0 && x != 21 && i <= secondi ; i++){
			    	
					differenza = secondi - i ;
										
					try {
						Thread.sleep(1000);
						/** Il thread si "addormenta"(non sarà in funzione) per 
						 *  1000 millisecondi = 1 secondo. */
						ha.post(new Runnable() {
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								tempo.setText(differenza + "");
							}
						});
				    } catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
				}
			    
				in = new Intent(Multiplayer.this, Attesa.class);
				in.putExtra("CONTATORE_1", contatore_1) ;
				startActivity(in);
			}
		}; new Thread(ru).start();	
	} 		
	
	/** Qui prendiamo le domande e le risposte dal database creato in locale. */
	
	public JSONObject informazioni(String lettera) throws JSONException{
			
		try{
			responseBody = "" ;
					
			DefaultHttpClient client = new DefaultHttpClient(); /** Client. */
			String url = "http://10.0.2.2:8080/Progetto" ; /** Url del Server. */
					
			HttpPost richiesta = new HttpPost(url);
			
            /** NameValuePairs è un oggetto che rappresenta una coppia nome-valore. */
			ArrayList<NameValuePair> CoppiaNomeValore = new ArrayList<NameValuePair>();
			CoppiaNomeValore.add(new BasicNameValuePair("LETTERA", lettera));
			richiesta.setEntity(new UrlEncodedFormEntity(CoppiaNomeValore)); 
			        
			/** ResponseHandler <String> permette di trasformare la risposta data dal server in
			 *  una stringa. */
			ResponseHandler<String> responseHandler = new BasicResponseHandler() ; 
			responseBody = client.execute(richiesta, responseHandler);
			obj = new JSONObject(responseBody);
			        
			Log.i("Http CoppiaNomeValore:", CoppiaNomeValore.toString());
			Log.i("Http JSON:", obj.toString());
			        
			client.getConnectionManager().shutdown(); /** Chiudiamo la connessione. */
								
			} catch(ClientProtocolException e){
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return obj ;
	}

}
