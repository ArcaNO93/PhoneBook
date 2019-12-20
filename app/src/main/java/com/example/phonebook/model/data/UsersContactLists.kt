package com.example.phonebook.model.data

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usersContactLists")
data class UsersContactLists (
        @PrimaryKey @ColumnInfo(name = "user_login") @NonNull var userName: String = "",
        @ColumnInfo(name = "user_contact_list") @NonNull var contacts: String = "" )

