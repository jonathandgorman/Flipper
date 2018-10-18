package com.jonathangorman.flipper.cards;

import java.io.Serializable;

public class Card implements Serializable{

    private static final String TAG = "Card";
    private String name = "";
    private String imagePath = "";
    private String audioPath = "";
    private String language = "";
    private String category = "";

    // card getters and setter
    public void setAudio(String audioPath) {
        this.audioPath = audioPath;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setImageName(String imagePath) {
        this.imagePath = imagePath;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAudioPath() {
        return audioPath;
    }
    public String getCategory() {
        return category;
    }
    public String getImageString() {
        return imagePath;
    }
    public String getLanguage() {
        return language;
    }
    public String getName() {
        return name;
    }
}
