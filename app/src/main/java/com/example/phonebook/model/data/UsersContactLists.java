package com.example.phonebook.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "usersContactLists")
public class UsersContactLists {

    @PrimaryKey
    @ColumnInfo(name = "user_login")
    @NonNull
    private String userName;

    @ColumnInfo(name = "user_contact_list")
    private String contacts;

    public UsersContactLists() {
        userName = "";
        contacts = "";
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }
}

