package com.example.phonebook.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.phonebook.model.dao.usersRepoDAO;
import com.example.phonebook.model.data.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UsersDB extends RoomDatabase {
    public abstract usersRepoDAO getUsersDB();
}
