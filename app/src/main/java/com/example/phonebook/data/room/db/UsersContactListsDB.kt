package com.example.phonebook.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.phonebook.data.room.dao.UsersContactsListsDAO
import com.example.phonebook.data.entities.UsersContactLists

@Database(entities = [UsersContactLists::class], version = 1, exportSchema = false)
abstract class UsersContactListsDB : RoomDatabase() {
    abstract fun getUsersContactListsDB(): UsersContactsListsDAO
}
