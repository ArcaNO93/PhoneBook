package com.example.phonebook.dagger.modules;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.phonebook.dagger.scopes.GlobalScope;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context appContext;

    public AppModule(Context context) {
        appContext = context;
    }

    @Provides
    @GlobalScope
    public Context provideContext() {
        return appContext;
    }

    @Provides
    @GlobalScope
    @Named("ServiceShP")
    public SharedPreferences provideServiceSharedPref() {
        return appContext.getSharedPreferences("Service", Context.MODE_PRIVATE);
    }

    @Provides
    @GlobalScope
    @Named("UsersShP")
    public SharedPreferences provideUsersSharedPref() {
        return appContext.getSharedPreferences("Users", Context.MODE_PRIVATE);
    }

    @Provides
    @GlobalScope
    @Named("ContactsShP")
    public SharedPreferences provideContactsSharedPref() {
        return appContext.getSharedPreferences("ContactList", Context.MODE_PRIVATE);
    }
}
