package com.example.phonebook.dagger.modules;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.model.data.User;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {

    @Provides
    @ActivitiesScope
    public User provideUser() {
        return new User();
    }
}
