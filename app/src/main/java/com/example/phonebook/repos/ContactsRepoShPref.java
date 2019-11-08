package com.example.phonebook.repos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.phonebook.othersToBeSort.Contact;

import java.util.ArrayList;

public class ContactsRepoShPref implements ContactsRepo {

    private static final String NO_CONTACT_ERROR = "Can't find contact";
    private static final String CONTACT_EXISTS_ERROR = "Contact already exists";

    private SharedPreferences mContacts;


    ContactsRepoShPref(@NonNull Application application) {
        Context context = application.getApplicationContext();
        mContacts = context.getSharedPreferences("ContactList", Context.MODE_PRIVATE);
    }

    @Override
    public void addContact(Contact _newContact) {

    }

    @Override
    public void deleteContact(String _contactName) {

    }

    @Override
    public Contact getContact(String _contactName) {
        return null;
    }

    @Override
    public void updateContact(String _contactName) {

    }

    @Override
    public ArrayList<Contact> getAllContacts() {
        return null;
    }
}
