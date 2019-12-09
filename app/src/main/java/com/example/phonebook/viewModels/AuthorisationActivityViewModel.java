package com.example.phonebook.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.phonebook.dagger.ComponentProvider;
import com.example.phonebook.model.repos.ServiceRepoShPref;

import javax.inject.Inject;

public class AuthorisationActivityViewModel extends AndroidViewModel {

    @Inject
    ServiceRepoShPref mServiceRepo;

    public AuthorisationActivityViewModel(@NonNull Application application) {
        super(application);
        ComponentProvider.getInstance().addActivityComponent().inject(this);
    }

    public boolean init() {
        return mServiceRepo.getSignedUp();
    }

}
