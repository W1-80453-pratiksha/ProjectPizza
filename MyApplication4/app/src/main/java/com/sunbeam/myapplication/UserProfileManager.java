package com.sunbeam.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class UserProfileManager {





        private static final String PREF_NAME = "pizza_shop";
        private static final String KEY_first_Name = "firstName";
        private static final String KEY_last_Name = "lastName";
        private static final String KEY_email = "email";
        private static final String KEY_ID = "id";

        public static void saveUser(Context context,int id, String firstName,String lastName,String email ) {
            SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.putString(KEY_first_Name, firstName);
            editor.putString(KEY_last_Name, lastName);
            editor.putString(KEY_email, email);
            editor.putInt(KEY_ID, id);

            editor.apply();
        }

        public static String get_firstName(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return preferences.getString(KEY_first_Name, "");
        }
    public static String get_lastName(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_last_Name, "");
    }
    public static String get_email(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return preferences.getString(KEY_email, "");
    }

        public static int getId(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            return preferences.getInt(KEY_ID, 0);
        }

        public static void clearData(Context context) {
            SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }

