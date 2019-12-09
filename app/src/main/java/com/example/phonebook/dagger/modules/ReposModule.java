package com.example.phonebook.dagger.modules;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.model.repos.ContactsRepoShPref;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

import dagger.Module;
import dagger.Provides;

@Module
public class ReposModule {

    @Provides
    @ActivitiesScope
    public ServiceRepoShPref provideServiceRepo() {
        return new ServiceRepoShPref();
    }

    @Provides
    @ActivitiesScope
    public UsersRepoShPref provideUsersRepo() {
        return new UsersRepoShPref();
    }

    @Provides
    @ActivitiesScope
    public ContactsRepoShPref provideContactsRepo() {
        return new ContactsRepoShPref();
    }
}
