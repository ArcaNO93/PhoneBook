package com.example.phonebook.dagger.components;

import com.example.phonebook.dagger.modules.ContactModule;
import com.example.phonebook.dagger.modules.ReposModule;
import com.example.phonebook.dagger.modules.UserModule;
import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.viewModels.AuthorisationActivityViewModel;
import com.example.phonebook.viewModels.LogInViewModel;
import com.example.phonebook.viewModels.MainActivityViewModel;
import com.example.phonebook.viewModels.RegisterViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = {ReposModule.class, UserModule.class, ContactModule.class})
@ActivitiesScope
public interface ActivityComponent {

    void inject(LogInViewModel logInViewModel);
    void inject(RegisterViewModel registerViewModel);
    void inject(AuthorisationActivityViewModel authorisationActivityViewModel);
    void inject(MainActivityViewModel mainActivityViewModel);
}
