package com.example.phonebook.model.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.phonebook.model.data.UsersContactLists;

@Dao
public interface usersContactsListsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertContactsList(UsersContactLists list);

    @Query("SELECT * FROM usersContactLists WHERE user_login = :userLogin")
    UsersContactLists getContactList(String userLogin);

    @Delete
    void deleteContactList(UsersContactLists list);
}
