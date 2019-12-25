package com.example.phonebook.dagger.modules

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

import androidx.room.Room

import com.example.phonebook.dagger.scopes.GlobalScope
import com.example.phonebook.model.db.UsersContactListsDB
import com.example.phonebook.model.db.UsersDB

import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    @GlobalScope
    fun provideServiceSharedPref(application: Application): SharedPreferences {
        return application.getSharedPreferences("Service", Context.MODE_PRIVATE)
    }

    @Provides
    @GlobalScope
    fun  provideUsersContactListsDB(application: Application): UsersContactListsDB {
        return Room.databaseBuilder(application, UsersContactListsDB::class.java, "usersContactLists.db").build()
    }

    @Provides
    @GlobalScope
    fun provideUsersDB(application: Application): UsersDB {
        return Room.databaseBuilder(application, UsersDB::class.java, "users.db").build()
    }
}
