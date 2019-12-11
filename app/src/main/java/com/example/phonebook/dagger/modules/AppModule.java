package com.example.phonebook.dagger.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.phonebook.dagger.scopes.GlobalScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @Provides
    @GlobalScope
    @Named("ServiceShP")
    public SharedPreferences provideServiceSharedPref(Application application) {
        return application.getSharedPreferences("Service", Context.MODE_PRIVATE);
    }

    @Provides
    @GlobalScope
    @Named("UsersShP")
    public SharedPreferences provideUsersSharedPref(Application application) {
        return application.getSharedPreferences("Users", Context.MODE_PRIVATE);
    }

    @Provides
    @GlobalScope
    @Named("ContactsShP")
    public SharedPreferences provideContactsSharedPref(Application application) {
        return application.getSharedPreferences("ContactList", Context.MODE_PRIVATE);
    }
}
