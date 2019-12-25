package com.example.phonebook.viewModels

import android.app.Application
import android.util.Log

import android.widget.Toast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.phonebook.model.data.Contact
import com.example.phonebook.model.repos.ContactsRepoByRoom
import com.example.phonebook.model.repos.ServiceRepoShPref

import java.util.ArrayList

import javax.inject.Inject

class MainActivityViewModel
@Inject constructor(
        private var mContactRepo: ContactsRepoByRoom,
        private var mServiceRepo: ServiceRepoShPref,
        var mContact: Contact,
        private var mApplication: Application): ViewModel() {

    var mCurrentContactList: ArrayList<Contact> = ArrayList()
    var mContacts: MutableLiveData<ArrayList<Contact>> = MutableLiveData()

    fun init() {
        mCurrentContactList.addAll(mContactRepo.getContactList(mServiceRepo.getCurrentUser() ?: "")?.asIterable() ?: ArrayList())
        mContacts.postValue(mCurrentContactList)
    }

    fun addContact(): Boolean {

        if(mContact.contactName.isEmpty()) {
            Toast.makeText(mApplication, "Contact is empty", Toast.LENGTH_SHORT).show()
            return false
        }

        val contact = Contact().apply {
            contactName = mContact.contactName
            contactPhone = mContact.contactPhone
            contactEmail = mContact.contactEmail
            contactAddress = mContact.contactAddress
        }

        val index = mCurrentContactList.indexOf(mContact)

        if(index < 0)
            mCurrentContactList.add(contact)
        else
            mCurrentContactList[index] = contact

        mContacts.postValue(mCurrentContactList)
        mContact.clean()
        return true
    }

    fun deleteContact() {
        mCurrentContactList.remove(mContact)
        mContacts.postValue(mCurrentContactList)
        mContact.clean()
    }

    fun signOut() = mServiceRepo.setSignedUp(false)

    fun save() = mContactRepo.saveContactList(mServiceRepo.getCurrentUser() ?: "", mCurrentContactList)
}
