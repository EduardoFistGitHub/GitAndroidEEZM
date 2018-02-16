package com.unam.aragon.fes.diplo.proyectosabadodiez;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button miBoton;
    private Button BotonDescarga;
    private TextView textoVista;
    private Bitmap ImgDescargada;
    private ImageView miImagen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        miBoton = findViewById(R.id.button);
        textoVista=findViewById(R.id.textoVista);
        miImagen=findViewById(R.id.imageView);



        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               textoVista.setText("Hola GitHub");
                Toast.makeText(MainActivity.this,
                        "Se cambio el mensaje  ",Toast.LENGTH_SHORT).show();

            }
        });


        miBoton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
                try{
                    new DescargarImagen().execute(new URL("https://i.ytimg.com/vi/O9FyfaWEf_c/hqdefault.jpg"));
                }catch (MalformedURLException e){
                    e.printStackTrace();
                }
           }
       });
    }

    class DescargarImagen extends AsyncTask <URL,Integer,Bitmap>{
        @Override
        protected Bitmap doInBackground(URL...urls){
            try{
                ImgDescargada = BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());
            }catch (Exception e){
                Log.e("diplo", "Error" + e.toString());
            }
            return ImgDescargada;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            miImagen.setImageBitmap(ImgDescargada);
            super.onPostExecute(bitmap);
        }

    }
}
