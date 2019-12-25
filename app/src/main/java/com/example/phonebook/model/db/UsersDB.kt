package com.example.phonebook.model.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.phonebook.model.dao.UsersRepoDAO
import com.example.phonebook.model.data.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDB : RoomDatabase() {
    abstract fun getUsersDB(): UsersRepoDAO
}
