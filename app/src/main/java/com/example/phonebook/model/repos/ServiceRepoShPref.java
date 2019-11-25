package com.example.phonebook.model.repos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class ServiceRepoShPref implements ServiceRepo{

    private SharedPreferences mService;

    public ServiceRepoShPref(@NonNull Application application) {
        Context context = application.getApplicationContext();
        mService = context.getSharedPreferences("Service", Context.MODE_PRIVATE);
    }

    @Override
    public void setCurrentUser(String _currentUser) {
        mService.edit().putString("Current user", _currentUser).apply();
    }

    @Override
    public void setSignedUp(boolean _flag) {
        mService.edit().putBoolean("Signed up", _flag).apply();
    }

    @Override
    public String getCurrentUser() {
        return mService.getString("Current user", "");
    }

    @Override
    public boolean getSignedUp() {
        return mService.getBoolean("Signed up", false);
    }
}
