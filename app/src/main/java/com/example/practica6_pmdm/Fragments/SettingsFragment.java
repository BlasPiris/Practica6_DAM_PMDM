package com.example.practica6_pmdm.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import androidx.preference.SwitchPreferenceCompat;

import com.example.practica6_pmdm.Activities.MainActivity;
import com.example.practica6_pmdm.R;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {

    //VARIABLES DE LOS SWITCH DE CONFIGURACIÓN
private SwitchPreferenceCompat audio,video,streaming;

//MAIN ACTIVITY
MainActivity ma;



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        //OBTENEMOS UN OBJETO MAINACTIVITY
        ma= (MainActivity) getActivity();

        //PREDEFINIMOS LA POSCION DE LOS SWITCH DEPENDIENDO DE LA CONFIGURACION ACTUAL
        audio=findPreference("audio");
        audio.setOnPreferenceChangeListener(this);
        audio.setChecked(ma.getSettings()[0]);

        video=findPreference("video");
        video.setOnPreferenceChangeListener(this);
        video.setChecked(ma.getSettings()[1]);

        streaming=findPreference("streaming");
        streaming.setOnPreferenceChangeListener(this);
        streaming.setChecked(ma.getSettings()[2]);

    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        //CAMBIAMOS LA CONFIGURACION DE RECURSOS DE AUDIO
        if(preference==audio){
          if(audio.isChecked()){
              ma.changeSetting(0,false);
          }else{
              ma.changeSetting(0,true);
          }
        }

        //CAMBIAMOS LA CONFIGURACION DE RECURSOS DE VIDEO
        if(preference==video){
            if(video.isChecked()){
                ma.changeSetting(1,false);
            }else{
                ma.changeSetting(1,true);
            }
        }

        //CAMBIAMOS LA CONFIGURACION DE RECURSOS DE STREAMING
        if(preference==streaming){
            if(streaming.isChecked()){
                ma.changeSetting(2,false);
            }else{
                ma.changeSetting(2,true);
            }
        }

        return true;
    }
}