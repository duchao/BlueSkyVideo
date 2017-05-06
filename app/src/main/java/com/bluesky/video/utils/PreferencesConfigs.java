package com.bluesky.video.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.bluesky.video.app.App;

public class PreferencesConfigs {
    private SharedPreferences mSp;
    public static String PREFERENCE_NAME = "MGTVCommon";
    public static final String IS_USER_REGISTER = "is_user_register";
    private static class SingletonHolder {
        private static final PreferencesConfigs INSTANCE = new PreferencesConfigs();
    }
    public static PreferencesConfigs getInstance() {
        return  SingletonHolder.INSTANCE;
    }

    public PreferencesConfigs() {
        mSp = App.getInstance().getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
    }

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public PreferencesConfigs set(String key, String value) {
        mSp.edit().putString(key, value).commit();
        return this;
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public PreferencesConfigs set(String key, boolean value) {
        mSp.edit().putBoolean(key, value).commit();
        return this;
    }

    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public PreferencesConfigs set(String key, int value) {
        mSp.edit().putInt(key, value).commit();
        return this;
    }

    public long getLong(String key, long defValue) {
        return mSp.getLong(key, defValue);
    }

    public void set(String key, long value) {
        mSp.edit().putLong(key, value).commit();
    }

}
