package com.example.phonebook.model.repos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.service.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;

public class ContactsRepoShPref implements ContactsRepo {

    private SharedPreferences mContacts;
    private SharedPreferences mUsers;

    public ContactsRepoShPref(@NonNull Application application) {
        Context context = application.getApplicationContext();
        mContacts = context.getSharedPreferences("ContactList", Context.MODE_PRIVATE);
        mUsers = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
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
    public void saveContactList(@NonNull String user, ArrayList<Contact> contactList) {
        try {
            mContacts.edit().putString(user, ObjectSerializer.serialize(contactList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
