package com.example.playerfragmented;

import java.io.Serializable;


public class Song implements Serializable {
    String title;
    String artist;
    String path;
    int id;

    public Song() {
        this.title = title;
        this.artist = artist;
        this.path = path;
        this.id = id;
    }

    //setters and getters
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    //Serializable





}
