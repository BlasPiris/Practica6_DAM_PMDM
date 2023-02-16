package com.example.practica6_pmdm.Activities;

import static com.example.practica6_pmdm.R.menu.settingmenu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ClipData;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.practica6_pmdm.Fragments.SettingsFragment;
import com.example.practica6_pmdm.R;

import java.util.Arrays;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    Boolean[] settings={true, true, true};

    public Boolean[] getSettings() {
        return settings;
    }

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

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {
        if(item.getItemId()==R.id.settings){
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