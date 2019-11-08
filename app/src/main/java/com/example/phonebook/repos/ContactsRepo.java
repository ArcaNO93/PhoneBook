package com.example.phonebook.repos;

import com.example.phonebook.othersToBeSort.Contact;

import java.util.ArrayList;

public interface ContactsRepo {
    void addContact(Contact _newContact);
    void deleteContact(String _contactName);
    Contact getContact(String _contactName);
    void updateContact(String _contactName);
    ArrayList<Contact> getAllContacts();
}
