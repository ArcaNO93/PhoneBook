package com.example.phonebook.model.repos

import com.example.phonebook.model.data.Contact
import com.example.phonebook.model.data.UsersContactLists
import com.example.phonebook.model.db.UsersContactListsDB
import com.example.phonebook.utils.ComponentProvider

import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.Executors

import javax.inject.Inject

class ContactsRepoByRoom @Inject constructor() : ContactsRepo {

    init {
        ComponentProvider.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var mUsersContactLists: UsersContactListsDB

    override fun getContactList(currentUser: String): ArrayList<Contact>? {
        val getAllContacts: Callable<ArrayList<Contact>?> = Callable {
            mUsersContactLists.getUsersContactListsDB().getContactList(currentUser)?.let {
                objectFromString(mUsersContactLists.getUsersContactListsDB().getContactList(currentUser)!!.contacts)
            }
        }

        return Executors.newSingleThreadExecutor().submit(getAllContacts).get()
    }

    override fun saveContactList(user: String, contactList: ArrayList<Contact>) {
        val foo = UsersContactLists().apply {
            userName = user
            contacts = stringFromObject(contactList)
        }

        val saveContactList = Callable {
            mUsersContactLists.getUsersContactListsDB().insertContactsList(foo)
        }

        return Executors.newSingleThreadExecutor().submit(saveContactList).get()
    }
}
