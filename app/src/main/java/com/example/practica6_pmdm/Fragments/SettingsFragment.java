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

private SwitchPreferenceCompat audio,video,streaming;

MainActivity ma;



    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        ma= (MainActivity) getActivity();

        audio=findPreference("audio");
        audio.setOnPreferenceChangeListener(this);
        audio.setChecked(ma.getSettings()[0]);

        video=findPreference("video");
        video.setOnPreferenceChangeListener(this);
        video.setChecked(ma.getSettings()[1]);

        streaming=findPreference("streaming");
        streaming.setOnPreferenceChangeListener(this);
        streaming.setChecked(ma.getSettings()[2]);


        ma= (MainActivity) getActivity();







    }


    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if(preference==audio){
          if(audio.isChecked()){
              ma.changeSetting(0,false);
          }else{
              ma.changeSetting(0,true);
          }
        }

        if(preference==video){
            if(video.isChecked()){
                ma.changeSetting(1,false);
            }else{
                ma.changeSetting(1,true);
            }
        }

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