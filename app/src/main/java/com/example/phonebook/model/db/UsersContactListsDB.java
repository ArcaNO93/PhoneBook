package com.example.phonebook.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.phonebook.model.dao.usersContactsListsDAO;
import com.example.phonebook.model.data.UsersContactLists;

@Database(entities = UsersContactLists.class, version = 1, exportSchema = false)
public abstract class UsersContactListsDB extends RoomDatabase {
    public abstract usersContactsListsDAO getUsersContactListsDB();
}
