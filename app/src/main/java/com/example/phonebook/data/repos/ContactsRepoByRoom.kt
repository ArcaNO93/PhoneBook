package com.example.phonebook.data.repos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.Contact
import com.example.phonebook.data.entities.UsersContactLists
import com.example.phonebook.data.room.db.UsersContactListsDB
import com.example.phonebook.utils.ComponentProvider
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

import java.util.concurrent.Callable
import java.util.concurrent.Executors

import javax.inject.Inject
import kotlin.collections.ArrayList

@ActivitiesScope
class ContactsRepoByRoom @Inject constructor(): ContactsRepo {

    init {
        ComponentProvider.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var mUsersContactLists: UsersContactListsDB

    override fun getLiveContactList(currentUser: String): LiveData<ArrayList<Contact>> {
        val liveList: LiveData<UsersContactLists> =  mUsersContactLists.getUsersContactListsDB().getLiveContactList(currentUser)
        val mediatorLiveData: MediatorLiveData<ArrayList<Contact>> = MediatorLiveData()
        mediatorLiveData.addSource(mUsersContactLists.getUsersContactListsDB().getLiveContactList(currentUser)) {
            if(it == null || it.contacts.isEmpty()) {
                mediatorLiveData.value = ArrayList()
            } else {
                mediatorLiveData.removeSource(liveList)
                mediatorLiveData.value = objectFromString(it.contacts)
            }
        }
        return mediatorLiveData
    }

    override fun getContactList(currentUser: String): ArrayList<Contact> {
        return Executors.newSingleThreadExecutor().submit( Callable {
            if (mUsersContactLists.getUsersContactListsDB().getContactList(currentUser)?.contacts != null)
                objectFromString(mUsersContactLists.getUsersContactListsDB().getContactList(currentUser)?.contacts)
            else
                ArrayList()
        }).get()
    }

    override fun saveContactList(user: String, contactList: ArrayList<Contact>) {

        val foo = UsersContactLists().apply {
            userName = user
            contacts = Gson().toJson(contactList)
        }

        val saveContactList = Callable {
            mUsersContactLists.getUsersContactListsDB().insertContactsList(foo)
        }

        return Executors.newSingleThreadExecutor().submit(saveContactList).get()
    }

    private fun objectFromString(list: String?): ArrayList<Contact> {
        return Gson().fromJson(list, object: TypeToken<ArrayList<Contact>>(){}.type)
    }
}
