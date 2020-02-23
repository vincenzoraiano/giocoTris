package com.example.giocotris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1 rappresenta il nero
    //0 rappresenta il rosso
    //2 rappresenta lo stato di vuoto all'interno della griglia.

    //array che rappresenta lo stato di vuoto.
    int[] statodelgioco={2, 2 ,2 ,2 ,2, 2, 2, 2, 2};

    //creo un array per le probabibili posizioni di vittria.
    int [][]posizioniDiVittoria={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};


    //uso questa variabile per capire quando un giocatore preme qualche ImageViev,
    //il giocatore inziale risulta essere sempre il giocatore rosso.
    int giocatoreAttivo=0;

    boolean giocoAttivo=true;


    //creo il metodo che permette di spostare dall'altro verso il basso le varie immagini quando vengono
    //premute, questo metodo viene usato per tutti gli elementi del layout.
    public void spostagiu(View view){



        //creo una variabile di tipo image viev,senza usare il findVieviId cosi puo essere applicata a tutte le immagini.
        ImageView counter=(ImageView) view;

        //uso questo log per controllare se le tag corrispondono alla giusta immagine,casto il valore in stringa.
        Log.i("Tag",counter.getTag().toString());

        int counterUsato=Integer.parseInt(counter.getTag().toString());

        //costrutto di controllo per controllare se le caselle non sono vuote, eleminando il problema di
        //cambiare la chip nella casella.
        if(statodelgioco[counterUsato]==2 && giocoAttivo) {


            statodelgioco[counterUsato] = giocatoreAttivo;

            //in questo modo tutte le image viev presenti nel progetto vengono spostate automaticamente fuori dal layout.
            counter.setTranslationY(-1500);

            //costrutto di controllo
            if (giocatoreAttivo == 0) {
                //quando il giocatore attivo e 0 usiamo la chip rossa
                counter.setImageResource(R.drawable.rossa);

                giocatoreAttivo = 1;
            } else {
                //quando il giocatore è 1 l'immage usata è quella nera
                counter.setImageResource(R.drawable.nera);

                giocatoreAttivo = 0;

            }

            //in questo modo riesco ad animare le image viev usate nel progetto.
            counter.animate().translationYBy(1500).rotation(3600).setDuration(1000);

            //Utilizzo un for each loop per iterare attraverso gli elementi.
            for (int[] posizioniDiVittoria : posizioniDiVittoria) {

                if (statodelgioco[posizioniDiVittoria[0]] == statodelgioco[posizioniDiVittoria[1]] && statodelgioco[posizioniDiVittoria[1]] == statodelgioco[posizioniDiVittoria[2]] && statodelgioco[posizioniDiVittoria[0]] != 2) {

                    giocoAttivo=false;
                    String vincitore="";
                    if (giocatoreAttivo == 1) {
                        vincitore="Rosso";
                    } else {
                        vincitore="Nero";
                    }


                        Button giocaAncora=(Button) findViewById(R.id.giocaAncoraId);

                        TextView testoVincitore=(TextView) findViewById(R.id.testoVincitoreId);

                        testoVincitore.setText(vincitore+" ha vinto");

                        giocaAncora.setVisibility(View.VISIBLE);

                        testoVincitore.setVisibility(View.VISIBLE);

                }
            }

        }

    }

    //metodo per l'utilizzo del tasto gioca ancora.
        public void giocaAncora(View view){
            Button giocaAncora=(Button) findViewById(R.id.giocaAncoraId);

            TextView testoVincitore=(TextView) findViewById(R.id.testoVincitoreId);

            giocaAncora.setVisibility(View.INVISIBLE);

            testoVincitore.setVisibility(View.INVISIBLE);

            GridLayout griglia=(GridLayout) findViewById(R.id.grigliaId);

            for(int i=0; i<griglia.getChildCount();i++){

                ImageView counter=(ImageView) griglia.getChildAt(i);

                counter.setImageDrawable(null);
            }

            for(int i=0;i<statodelgioco.length;i++) {

                statodelgioco[i]=2;
            }
            giocatoreAttivo=0;

            giocoAttivo=true;

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
