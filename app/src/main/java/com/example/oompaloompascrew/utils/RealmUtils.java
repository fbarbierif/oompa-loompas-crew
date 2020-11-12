package com.example.oompaloompascrew.utils;

import android.content.Context;

import com.example.oompaloompascrew.dto.OLWorkerDTO;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;

public abstract class RealmUtils {

    private static final String GENDER_KEY = "gender";

    /**
     * Realm initializer
     *
     * @param context the context
     */
    public static void initializeRealm(final Context context) {
        Realm.init(context);
        final RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * Delete all stored olWorkers in db
     */
    public static void deleteFromRealmWithoutKey() {
        final Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            RealmResults<OLWorkerDTO> result = realm1.where(OLWorkerDTO.class).findAll();
            result.deleteAllFromRealm();
        });
        realm.close();
    }

    /**
     * Store searched object (key/olWorkers)
     *
     * @param olWorkersList the workers to store
     */
    public static void storeResults(final RealmList<OLWorkerDTO> olWorkersList) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.insertOrUpdate(olWorkersList);
        });
        realm.close();
    }

    /**
     * Retrieve the object stores in db by the key
     */
    public static ArrayList<OLWorkerDTO> restoreSearchFromDB() {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults search = realm.where(OLWorkerDTO.class).findAll();
        ArrayList<OLWorkerDTO> results = new ArrayList<>(realm.copyFromRealm(search));
        realm.close();
        return results;
    }

    /**
     * Retrieve the object stores in db by the key to filter
     */
    public static ArrayList<OLWorkerDTO> restoreSearchFromDBByGender(String gender) {
        final Realm realm = Realm.getDefaultInstance();
        RealmResults search = realm.where(OLWorkerDTO.class).equalTo(GENDER_KEY, gender).findAll();
        ArrayList<OLWorkerDTO> results = new ArrayList<>(realm.copyFromRealm(search));
        realm.close();
        return results;
    }

}
