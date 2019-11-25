package com.example.phonebook.model.repos;

import com.example.phonebook.model.data.Contact;

import java.util.ArrayList;

public interface ContactsRepo {
    ArrayList<Contact> getAllContacts(String _currentUser);
    void saveContactList(String _user, ArrayList<Contact> _contactList);
}
