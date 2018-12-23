package com.example.oliverboamah.banku.feature.util;

/**
 * Created by codingoliver on 8/9/2018.
 */

import android.content.Context;
import android.content.SharedPreferences;

import com.example.oliverboamah.banku.feature.data.UserData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * PreferencesUtils, easy to get or put data
 * <ul>
 * <strong>Preference Name</strong>
 * <li>you can change preference name by {@link #PREFERENCE_NAME}</li>
 */
public class PreferencesUtil {

    public static String PREFERENCE_NAME = "fingerPreferencesUtil05403435";
    public static String KEY_IS_FIRST_TIME = "isFirstTime";

    private PreferencesUtil() {
        throw new AssertionError();
    }


    public static ArrayList<UserData> getUsersData(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(PREFERENCE_NAME, null); //Retrieve previously saved data
        Type type = new TypeToken<ArrayList<UserData>>() {
        }.getType();
        return json == null ? new ArrayList<UserData>() : (ArrayList<UserData>) gson.fromJson(json, type);
    }

    public static void saveUsersData(Context context, ArrayList<UserData> usersData) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(usersData); //Convert the array to json
        editor.putString(PREFERENCE_NAME, json); //Put the variable in memory
        editor.apply();
        editor.commit();
    }


    public static boolean setNoMoreFirstTimer(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEY_IS_FIRST_TIME, false);
        return editor.commit();
    }

    public static boolean isFirstTime(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(KEY_IS_FIRST_TIME, true);
    }

}