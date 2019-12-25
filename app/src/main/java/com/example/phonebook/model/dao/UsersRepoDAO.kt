package com.example.phonebook.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import com.example.phonebook.model.data.User;

@Dao
interface UsersRepoDAO {

    @Insert
    fun insertUser(users: User): Long

    @Query("SELECT * FROM users WHERE user_login = :login")
    fun getUser(login: String): User

    @Delete
    fun deleteUser(user: User)
}
