package com.itla.postapp.preference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.itla.postapp.R;

public class TokenPreference implements Preference<String> {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    private Activity activity;

    public TokenPreference(Activity activity){
        this.activity = activity;
        sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();
    }

    @Override
    public void write(String value) {
        Resources resources = activity.getResources();
        String savedTokenKey = resources.getString(R.string.saved_token);
        editor.putString(savedTokenKey, value);
        editor.commit();
    }

    @Override
    public String read() {
        Resources resources = activity.getResources();
        String tokenValue = resources.getString(R.string.saved_token);
        String token = sharedPref.getString(tokenValue, tokenValue);
        return token;
    }
}
