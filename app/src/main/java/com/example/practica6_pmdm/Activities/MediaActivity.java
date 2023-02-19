package com.example.practica6_pmdm.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.practica6_pmdm.R;

public class MediaActivity extends AppCompatActivity {

    //OBJETO VIDEOVIEW
    VideoView videoView;

    //OBJETO MEDIACONTROLLER
     MediaController vidControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);

        vidControl = new MediaController(this);
        videoView = findViewById(R.id.videoView);

        //OCULTAMOS LA BARRA SUPERIOR
        getSupportActionBar().hide();

        //OBTENEMOS LA INFORMACION DE VIDEOS Y STREAMINGS
        Intent i=getIntent();
        int idUri=i.getExtras().getInt("Uri");
        String link=i.getExtras().getString("link");
        String type=i.getExtras().getString("type");



        if(type.equals("1")){
            //SI ES UN VIDEO, CARGAMOS EL VIDEO DE RAW
            String path = "android.resource://" + getPackageName() + "/" + idUri;
            videoView.setVideoURI(Uri.parse(path));
        }else{
            //SI ES UN STRAMING, CARGAMOS EL ENLACE
            videoView.setVideoPath(link);
        }

        //CREAMOS EL CONTROLADOR DEL VIDEO
        vidControl.setAnchorView(videoView);
        videoView.setMediaController(vidControl);

        //REPRODUCIMOS
        videoView.start();
    }
}