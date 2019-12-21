package com.example.phonebook.model.repos

import com.example.phonebook.model.data.Contact
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.ArrayList

interface ContactsRepo {
    fun getContactList(currentUser: String): ArrayList<Contact>?
    fun saveContactList(user: String,  contactList: ArrayList<Contact>)

    fun stringFromObject(list: ArrayList<Contact>): String {
        return Gson().toJson(list)
    }

     fun objectFromString(list: String): ArrayList<Contact> {
         return Gson().fromJson(list, object: TypeToken<ArrayList<Contact>>(){}.type)
    }
}
