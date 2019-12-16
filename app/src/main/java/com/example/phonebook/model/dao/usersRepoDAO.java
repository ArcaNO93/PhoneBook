package com.example.phonebook.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.phonebook.model.data.User;

@Dao
public interface usersRepoDAO {

    @Insert()
    long insertUser(User users);

    @Query("SELECT * FROM users WHERE user_login = :login")
    User getUser(String login);

    @Delete
    void deleteUser(User user);
}
