package com.example.phonebook.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.room.Room;

import com.example.phonebook.dagger.scopes.GlobalScope;
import com.example.phonebook.model.db.UsersContactListsDB;
import com.example.phonebook.model.db.UsersDB;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @GlobalScope
    public SharedPreferences provideServiceSharedPref(Application application) {
        return application.getSharedPreferences("Service", Context.MODE_PRIVATE);
    }

    @Provides
    @GlobalScope
    public UsersContactListsDB provideUsersContactListsDB(Application application) {
        return Room.databaseBuilder(application, UsersContactListsDB.class, "usersContactLists.db").build();
    }

    @Provides
    @GlobalScope
    public UsersDB provideUsersDB(Application application) {
        return Room.databaseBuilder(application, UsersDB.class, "users.db").build();
    }
}
