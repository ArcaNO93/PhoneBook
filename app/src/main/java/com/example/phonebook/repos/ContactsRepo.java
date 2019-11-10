package com.example.phonebook.repos;

import com.example.phonebook.othersToBeSort.Contact;

import java.util.ArrayList;

public interface ContactsRepo {
    void addContact(Contact _newContact);
    void deleteContact(int _contactPosition);
    Contact getContact(int _contactPosition);
    ArrayList<Contact> getAllContacts();
    void updateContact(int _contactPosition, String _newName, String _newPone, String _newEmail, String _newAddress);
}
