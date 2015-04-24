package com.rosanarogiski.notes.bean;

import android.content.Context;

import com.mauriciogiordano.easydb.bean.Model;

import java.util.List;

/**
 * Created by mauricio on 4/21/15.
 */
public class Note extends Model<Note> implements Comparable<Note> {

    @ModelField
    private String id;

    @ModelField
    private String author;

    @ModelField
    private String title;

    @ModelField
    private String subject;

    @ModelField
    private String course;

    @ModelField
    private String tags;

    @ModelField
    private List<String> imagesSrc;

    @ModelField
    private float rating;

    @ModelField
    private long timestamp;

    @ModelField
    private boolean upload;

    @ModelField
    private boolean download;

    @ModelField
    private boolean visualized;


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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getImagesSrc() {
        return imagesSrc;
    }

    public void setImagesSrc(List<String> imagesSrc) {
        this.imagesSrc = imagesSrc;
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

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
    }

    public boolean isVisualized() {
        return visualized;
    }

    public void setVisualized(boolean visualized) {
        this.visualized = visualized;
    }

    @Override
    public int compareTo(Note another) {
        return (int) (another.getTimestamp() - timestamp);
    }
}
