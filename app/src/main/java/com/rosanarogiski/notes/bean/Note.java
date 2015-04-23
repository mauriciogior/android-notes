package com.rosanarogiski.notes.bean;

import android.content.Context;
import android.graphics.Bitmap;

import com.mauriciogiordano.easydb.bean.Model;

/**
 * Created by mauricio on 4/21/15.
 */
public class Note extends Model<Note> {

    @ModelField
    private String id;

    @ModelField
    private String title;

    @ModelField
    private String imageSrc;

    @ModelField
    private float rating;

    @ModelField
    private long timestamp;

    private Bitmap image;

    public Note() { super(Note.class, true); }

    public Note(Context context) {
        super(Note.class, true, context);
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

}
