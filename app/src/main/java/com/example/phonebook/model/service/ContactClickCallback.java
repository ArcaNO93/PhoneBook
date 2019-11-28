package com.example.phonebook.model.service;

import androidx.annotation.NonNull;

import com.example.phonebook.model.data.Contact;

public interface ContactClickCallback {
    void onClick(@NonNull Contact contact);
}
