package com.example.phonebook.model.service

import com.example.phonebook.model.data.Contact

interface ContactClickCallback {
    fun onClick(contact: Contact)
}
