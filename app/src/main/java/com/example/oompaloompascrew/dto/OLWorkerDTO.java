package com.example.oompaloompascrew.dto;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class OLWorkerDTO implements RealmModel {

    private int id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    private FavoriteDTO favorite;
    private String gender;
    private String image;
    private String profession;
    private String email;
    private int age;
    private String country;
    private int height;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getImage() {
        return image;
    }

    public String getProfession() {
        return profession;
    }
}
