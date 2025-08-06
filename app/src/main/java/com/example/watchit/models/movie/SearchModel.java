package com.example.watchit.models.movie;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SearchModel {
    @SerializedName("text")
    private String text;

    public SearchModel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }
}
