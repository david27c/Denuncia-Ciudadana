package com.example.denunciaciudadana;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesManager {
    private static final String PREF_NAME = "ProfilePrefs";

    public static void saveSettings(Context context, boolean showName, boolean showPic, boolean showEmail) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("show_name", showName);
        editor.putBoolean("show_pic", showPic);
        editor.putBoolean("show_email", showEmail);
        editor.apply();
    }

    public static boolean getShowName(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean("show_name", true);
    }

    public static boolean getShowPic(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean("show_pic", true);
    }

    public static boolean getShowEmail(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getBoolean("show_email", true);
    }
}