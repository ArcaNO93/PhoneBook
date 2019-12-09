package com.example.phonebook.dagger.components;

import com.example.phonebook.dagger.modules.AppModule;
import com.example.phonebook.dagger.scopes.GlobalScope;
import com.example.phonebook.model.repos.ContactsRepoShPref;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

import dagger.Component;

@Component(modules = {AppModule.class})
@GlobalScope
public interface AppComponent {
    ActivityComponent addActivityComponent();

    void inject(ContactsRepoShPref contactsRepoShPref);
    void inject(ServiceRepoShPref serviceRepoShPref);
    void inject(UsersRepoShPref usersRepoShPref);
}
