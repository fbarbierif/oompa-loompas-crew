package com.example.oompaloompascrew.response;

import com.example.oompaloompascrew.dto.OLWorkerDTO;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.annotations.RealmClass;

@RealmClass
public class OLWorkersResponse implements RealmModel {

    @SerializedName("current")
    private int currentPage;
    @SerializedName("total")
    private int resultsCount;
    @SerializedName("results")
    private RealmList<OLWorkerDTO> OLWorkersList;

    public int getCurrentPage() {
        return currentPage;
    }

    public RealmList<OLWorkerDTO> getOLWorkersList() {
        return OLWorkersList;
    }
}
