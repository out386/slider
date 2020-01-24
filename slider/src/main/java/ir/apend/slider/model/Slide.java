package ir.apend.slider.model;


import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Farzad Farazmand on 28,June,2017
 * farzad.farazmand@gmail.com
 */

public class Slide implements Serializable{

    private int id;
    private Uri imageUri;
    private int imageCorner;

    public Slide(int id, Uri imageUri,int imageCorner) {
        this.id = id;
        this.imageUri = imageUri;
        this.imageCorner = imageCorner;
    }

    public int getId() {
        return id;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public int getImageCorner() {
        return imageCorner;
    }

    public void setImageCorner(int imageCorner) {
        this.imageCorner = imageCorner;
    }
}
