package com.example.phonebook.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import com.example.phonebook.dagger.scopes.ActivitiesScope

import java.util.UUID

import javax.inject.Inject

@Entity(tableName = "users")
@ActivitiesScope
data class User (
        @PrimaryKey @ColumnInfo(name = "user_id") val ID: String,
        @ColumnInfo(name = "user_login") var login: String,
        @ColumnInfo(name = "user_password") var password: String) {

    @Inject constructor(): this(UUID.randomUUID().toString(),"", "")

    fun clean() {
        login = ""
        password = ""
    }
}