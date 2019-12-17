package com.example.phonebook.model.repos;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.utils.ComponentProvider;

import javax.inject.Inject;

@ActivitiesScope
public class ServiceRepoShPref implements ServiceRepo {

    @Inject
    SharedPreferences mService;

    @Inject
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
