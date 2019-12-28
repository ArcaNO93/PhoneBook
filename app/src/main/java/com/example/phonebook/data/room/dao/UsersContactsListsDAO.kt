package com.example.phonebook.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.phonebook.data.entities.UsersContactLists

@Dao
interface UsersContactsListsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContactsList(list: UsersContactLists)

    @Query("SELECT * FROM usersContactLists WHERE user_login = :userLogin")
    fun getLiveContactList(userLogin: String): LiveData<UsersContactLists>

    @Query("SELECT * FROM usersContactLists WHERE user_login = :userLogin")
    fun getContactList(userLogin: String): UsersContactLists?

    @Delete
    fun deleteContactList(list: UsersContactLists)
}
