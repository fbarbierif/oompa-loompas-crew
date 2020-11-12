package com.example.oompaloompascrew.dto;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class FavoriteDTO implements RealmModel {

    private String color;
    private String food;
    @SerializedName("random_string")
    private String randomString;
    private String song;

}
