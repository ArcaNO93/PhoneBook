package com.example.phonebook.data.repos

import androidx.lifecycle.LiveData
import com.example.phonebook.data.entities.Contact

import java.util.ArrayList

interface ContactsRepo {
    fun getLiveContactList(currentUser: String): LiveData<ArrayList<Contact>>
    fun getContactList(currentUser: String): ArrayList<Contact>
    fun saveContactList(user: String,  contactList: ArrayList<Contact>)
}
