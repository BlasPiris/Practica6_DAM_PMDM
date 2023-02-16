package com.example.practica6_pmdm;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.collection.ArraySet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ResourcesContent {

    //METODO QUE CARGA LOS DATOS DEL JSON
    public ArrayList<Resource> loadResourcesFromJSON(Context c) {

        String json = null;
        ArrayList<Resource> resourceItems = new ArrayList<Resource>();


        try {
            InputStream is =
                    c.getAssets().open("recursosList.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject jsonObject = new JSONObject(json);
            JSONArray couchList = jsonObject.getJSONArray("recursos_list");
            for (int i = 0; i < couchList.length(); i++) {
                JSONObject jsonCouch = couchList.getJSONObject(i);
                String name = jsonCouch.getString("nombre");
                String description = jsonCouch.getString("descripcion");
                String type = jsonCouch.getString("tipo");
                String uri = jsonCouch.getString("URI");
                Bitmap photo = null;
                try {
                    photo = BitmapFactory.decodeStream(
                            c.getAssets().open("images/" +
                                    jsonCouch.getString("imagen")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                resourceItems.add(new Resource(name, description, type, uri, photo));
            }
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return resourceItems;
    }
}
