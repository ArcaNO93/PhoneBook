package com.example.phonebook.dagger.components;

import com.example.phonebook.dagger.modules.ContactModule;
import com.example.phonebook.dagger.modules.ReposModule;
import com.example.phonebook.dagger.modules.UserModule;
import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.viewModels.LogInViewModel;
import com.example.phonebook.viewModels.MainActivityViewModel;
import com.example.phonebook.viewModels.RegisterViewModel;

import dagger.Subcomponent;

@Subcomponent(modules = {ReposModule.class, UserModule.class, ContactModule.class})
@ActivitiesScope
public interface ActivityComponent {

    @Subcomponent.Builder
    interface Builder {
        ActivityComponent.Builder reposModule(ReposModule reposModule);
        ActivityComponent.Builder userModule(UserModule userModule);
        ActivityComponent.Builder contactsModule(ContactModule contactModule);
        ActivityComponent build();
    }

    void inject(LogInViewModel logInViewModel);
    void inject(RegisterViewModel registerViewModel);
    void inject(MainActivityViewModel mainActivityViewModel);
}
