package com.example.phonebook.model.repos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phonebook.model.data.Contact;

import java.util.ArrayList;

public interface ContactsRepo {
    ArrayList<Contact> getAllContacts(@NonNull String currentUser);
    void saveContactList(@NonNull String user, @Nullable ArrayList<Contact> contactList);
}
