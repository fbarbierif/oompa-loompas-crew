package com.example.oompaloompascrew.response;

import com.example.oompaloompascrew.dto.FavoriteDTO;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class OLWorkerDetailResponse implements RealmModel {

    private String quota;
    private String description;
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

    public String getQuota() {
        return quota;
    }

    public String getDescription() {
        return description;
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

    public int getAge() {
        return age;
    }

}
