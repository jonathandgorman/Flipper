package com.jonathangorman.lorlingo.domain;

import java.io.Serializable;

public class CardItem implements Serializable{

    private String nameId = "";
    private String imageId = "";
    private String audioString = "";
    private String language = "";
    private String category = "";

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getAudioString() {
        return audioString;
    }

    public void setAudioString(String audioString) {
        this.audioString = audioString;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "nameId='" + nameId + '\'' +
                ", imageId='" + imageId + '\'' +
                ", audioString='" + audioString + '\'' +
                ", language='" + language + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
