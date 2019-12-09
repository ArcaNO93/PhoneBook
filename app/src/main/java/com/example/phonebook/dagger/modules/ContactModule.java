package com.example.phonebook.dagger.modules;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.model.data.Contact;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {

    @Provides
    @ActivitiesScope
    public Contact provideContact() {
        return new Contact();
    }
}
