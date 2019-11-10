package com.example.phonebook.repos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.example.phonebook.othersToBeSort.Contact;
import com.example.phonebook.othersToBeSort.ContactAdapter;
import com.example.phonebook.othersToBeSort.ObjectSerializer;

import java.io.IOException;
import java.util.ArrayList;

public class ContactsRepoShPref implements ContactsRepo {

    private SharedPreferences mContacts;
    private SharedPreferences mUsers;
    private ArrayList<Contact> mUserContactList;
    private ContactAdapter adapter;

    public ContactsRepoShPref(@NonNull Application application) {
        Context context = application.getApplicationContext();
        mContacts = context.getSharedPreferences("ContactList", Context.MODE_PRIVATE);
        mUsers = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
        adapter = new ContactAdapter(context, mUserContactList);
        mUserContactList = getAllContacts();
    }

    @Override
    public void addContact(Contact _newContact) {
        mUserContactList.add(_newContact);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteContact(int _contactPosition) {
        mUserContactList.remove(_contactPosition);
        adapter.notifyDataSetChanged();
    }

    @Override
    public Contact getContact(int _contactPosition) {
        return mUserContactList.get(_contactPosition);
    }

    @Override
    public void updateContact(int _contactPosition, String _newName, String _newPone, String _newEmail, String _newAddress) {
        Contact contact = mUserContactList.get(_contactPosition);
        contact.setContactName(_newName);
        contact.setContactPhone(_newPone);
        contact.setContactEmail(_newEmail);
        contact.setContactAddress(_newAddress);
    }

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<Contact> getAllContacts() {
        try {
            mUserContactList.addAll((ArrayList<Contact>) ObjectSerializer
                    .deserialize(mContacts
                            .getString(mUsers.getString("CurrentUser", ""), ObjectSerializer.serialize(new ArrayList<>()))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    return null;
    }
}
