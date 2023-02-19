package com.example.practica6_pmdm.Activities;

import static com.example.practica6_pmdm.R.menu.settingmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.practica6_pmdm.Fragments.SettingsFragment;
import com.example.practica6_pmdm.R;

import java.util.Arrays;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    //VARIABLE SELECCION RECURSOS
    Boolean[] settings={true, true, true};

    //OBJETO MEDIA PLAYER
    private MediaPlayer md;


    //SET DE LAS VARIABLES


    public void setMd(MediaPlayer md) {
        this.md = md;
    }

    public Boolean[] getSettings() {

        return settings;
    }

    //METODO QUC CAMBIA LA SELECCION DE RECURSOS
    public void changeSetting(int pos, Boolean value) {

        this.settings[pos]=value;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.settingmenu,menu);
        return true;
    }

    //METODO QUE NOS MOSTRARÁ LOS SETINGS AL PULSAR EL ENGRANAJE
    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
            if(md!=null){
                md.stop();
            }
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Fragment settings = new SettingsFragment();

            fragmentTransaction.replace(R.id.fragmentContainerView, settings);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        return true;
    }
}