package com.nazo.mathrapid;


        import android.content.Context;
        import android.content.SharedPreferences;
        import android.preference.PreferenceManager;

        import java.util.Locale;

/**
 * Created by mockingbird on 2/24/2018.
 */

public class MyData {


    ///////////////////String
    public static void setStringData(String key, String newvalue, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, newvalue).apply();
        editor.commit();
    }

    public static String getStringData(String key,String  defaultvalue ,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getString(key, defaultvalue);
    }

    ////////////Float
    public static void setFloatData(String key, Float newvalue, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(key, newvalue).apply();
        editor.commit();
    }

    public static Float getFloatData(String key,Float  defaultvalue ,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getFloat(key, defaultvalue);
    }

    /////////////////int

    public static void setIntData(String key, int newvalue, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, newvalue).apply();
        editor.commit();
    }

    public static int getInttData(String key,int  defaultvalue ,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getInt(key, defaultvalue);
    }
    ////////Boolees
    public static void setBooleanData(String key, Boolean newvalue, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, newvalue).apply();
        editor.commit();
    }

    public static Boolean getBooleanData(String key,Boolean  defaultvalue ,Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return preferences.getBoolean(key, defaultvalue);
    }





}
