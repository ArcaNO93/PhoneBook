package com.example.phonebook.dagger.components;

import android.app.Application;

import com.example.phonebook.dagger.modules.AppModule;
import com.example.phonebook.dagger.scopes.GlobalScope;
import com.example.phonebook.model.repos.ContactsRepoShPref;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class})
@GlobalScope
public interface AppComponent {
    ActivityComponent.Builder activityComponentBuilder();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder withApplication(Application application);
        AppComponent build();

    }

    void inject(ContactsRepoShPref contactsRepoShPref);
    void inject(ServiceRepoShPref serviceRepoShPref);
    void inject(UsersRepoShPref usersRepoShPref);
}
