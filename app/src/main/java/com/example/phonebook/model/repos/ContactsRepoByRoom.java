package com.example.phonebook.model.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.data.UsersContactLists;
import com.example.phonebook.model.db.UsersContactListsDB;
import com.example.phonebook.utils.ComponentProvider;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class ContactsRepoByRoom implements ContactsRepo {

    @Inject
    UsersContactListsDB mUsersContactLists;

    @Inject
    public ContactsRepoByRoom() {
        ComponentProvider.getInstance().getAppComponent().inject(this);
    }

    @Override
    public ArrayList<Contact> getContactList(@NonNull String currentUser) {

        Callable<ArrayList<Contact>> getAllContacts = () -> objectFromString(mUsersContactLists
                .getUsersContactListsDB()
                .getContactList(currentUser)
                .getContacts());

        try {
            return Executors.newSingleThreadExecutor().submit(getAllContacts).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public long saveContactList(@NonNull String user, @Nullable ArrayList<Contact> contactList) {
        UsersContactLists foo = new UsersContactLists();

        foo.setUserName(user);
        foo.setContacts(stringFromObject(contactList));

        Callable<Long> saveContactList = () -> mUsersContactLists.getUsersContactListsDB().insertContactsList(foo);

        try {
            return Executors.newSingleThreadExecutor().submit(saveContactList).get();
        } catch(ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

}
