package com.example.phonebook.utils

import com.example.phonebook.data.pojo.ContactView

interface ContactClickCallback {
    fun onClick(contact: ContactView)
}
