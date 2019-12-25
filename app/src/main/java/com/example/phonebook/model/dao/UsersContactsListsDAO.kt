package com.example.phonebook.model.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.phonebook.model.data.UsersContactLists

@Dao
interface UsersContactsListsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContactsList(list: UsersContactLists)

    @Query("SELECT * FROM usersContactLists WHERE user_login = :userLogin")
    fun getContactList(userLogin: String): UsersContactLists?

    @Delete
    fun deleteContactList(list: UsersContactLists )
}
