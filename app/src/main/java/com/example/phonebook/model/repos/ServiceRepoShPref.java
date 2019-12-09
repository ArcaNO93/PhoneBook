package com.example.phonebook.model.repos;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.phonebook.dagger.ComponentProvider;

import javax.inject.Inject;
import javax.inject.Named;

public class ServiceRepoShPref implements ServiceRepo {

    @Inject
    @Named("ServiceShP")
    SharedPreferences mService;

    public ServiceRepoShPref() {
        ComponentProvider.getInstance().getAppComponent().inject(this);
    }

    @Override
    public void setCurrentUser(@NonNull String currentUser) {
        mService.edit().putString("Current user", currentUser).apply();
    }

    @Override
    public void setSignedUp(boolean flag) {
        mService.edit().putBoolean("Signed up", flag).apply();
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
