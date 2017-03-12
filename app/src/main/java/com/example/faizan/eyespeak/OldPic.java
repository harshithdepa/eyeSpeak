package com.example.faizan.eyespeak;

public class OldPic {
    private int id;
    private String word;
    byte[] image;

    public OldPic(String word, byte[] image, int id) {
        this.word = word;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
