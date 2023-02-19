package com.example.practica6_pmdm.Pojos;

import android.graphics.Bitmap;
import android.net.Uri;

public class Resource {
    String name,description,type,uri;
    Bitmap image;

    public Resource() {
    }

    public Resource(String name, String description, String type, String uri, Bitmap image) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.uri = uri;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
