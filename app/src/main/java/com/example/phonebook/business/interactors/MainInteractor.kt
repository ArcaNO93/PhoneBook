package com.example.phonebook.business.interactors

import androidx.lifecycle.LiveData
import com.example.phonebook.data.pojo.ContactView

interface MainInteractor {
    fun addContact(contact: ContactView): Boolean
    fun updateContact(contact: ContactView): Boolean
    fun deleteContact(contact: ContactView)
    fun getContactList(): LiveData<ArrayList<ContactView>>
    fun logOut()
    fun save()
}