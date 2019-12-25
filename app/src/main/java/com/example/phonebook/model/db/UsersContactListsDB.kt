package com.example.phonebook.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.phonebook.model.dao.UsersContactsListsDAO
import com.example.phonebook.model.data.UsersContactLists

@Database(entities = [UsersContactLists::class], version = 1, exportSchema = false)
abstract class UsersContactListsDB : RoomDatabase() {
    abstract fun getUsersContactListsDB(): UsersContactsListsDAO
}
