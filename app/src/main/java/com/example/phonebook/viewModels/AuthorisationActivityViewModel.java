package com.example.phonebook.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.phonebook.model.repos.ServiceRepoShPref;

public class AuthorisationActivityViewModel extends AndroidViewModel {

    private ServiceRepoShPref mServiceRepo;

    public AuthorisationActivityViewModel(@NonNull Application application) {
        super(application);
        mServiceRepo = new ServiceRepoShPref(application);
    }

    public boolean init() {
        return mServiceRepo.getSignedUp();
    }
}
