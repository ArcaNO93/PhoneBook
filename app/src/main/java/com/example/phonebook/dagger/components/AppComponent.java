package com.example.phonebook.dagger.components;

import android.app.Application;

import com.example.phonebook.dagger.modules.AppModule;
import com.example.phonebook.dagger.scopes.GlobalScope;
import com.example.phonebook.model.repos.ContactsRepoByRoom;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoByRoom;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {AppModule.class})
@GlobalScope
public interface AppComponent {
    AuthActViewModelsComponent.Builder authActViewModelsComponent();
    MainActViewModelsComponent.Builder mainActViewModelsComponent();

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder withApplication(Application application);
        AppComponent build();

    }

    void inject(ContactsRepoByRoom contactsRepo);
    void inject(ServiceRepoShPref serviceRepo);
    void inject(UsersRepoByRoom usersRepo);
}
