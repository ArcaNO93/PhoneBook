package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.phonebook.repos.ContactsRepoShPref;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ContactsRepoShPref mRepo;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mRepo = new ContactsRepoShPref(application);
    }

    public void init() {

    }

    public void newContact() {

    }
}
