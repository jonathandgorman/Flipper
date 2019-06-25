package com.jonathangorman.lorlingo.domain;

/*
* Class representation of an in app Category choice
* */

public class CategoryItem {

    private String nameId = "";
    private String displayText = "";
    private int imageId = 0;

    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "CategoryItem{" +
                "internalName='" + nameId + '\'' +
                ", visibleName='" + displayText + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
