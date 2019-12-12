package com.example.phonebook.model.repos;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.service.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

@ActivitiesScope
public class ContactsRepoShPref implements ContactsRepo {

    @Inject
    @Named("ContactsShP")
    SharedPreferences mContacts;

    @Inject
    @Named("UsersShP")
    SharedPreferences mUsers;

    @Inject
    public ContactsRepoShPref() {
        ComponentProvider.getInstance().getAppComponent().inject(this);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Contact> getAllContacts(@NonNull String currentUser) {
        try {
            return new ArrayList<>((ArrayList<Contact>) ObjectSerializer
                    .deserialize(mContacts
                            .getString(mUsers.getString(currentUser, ""), ObjectSerializer.serialize(new ArrayList<>()))));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveContactList(@NonNull String user, @Nullable ArrayList<Contact> contactList) {
        try {
            mContacts.edit().putString(user, ObjectSerializer.serialize(contactList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
