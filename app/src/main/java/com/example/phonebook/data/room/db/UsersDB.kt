package com.example.phonebook.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase

import com.example.phonebook.data.room.dao.UsersRepoDAO
import com.example.phonebook.data.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UsersDB : RoomDatabase() {
    abstract fun getUsersDB(): UsersRepoDAO
}
