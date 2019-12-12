package com.example.phonebook.dagger.components;

import com.example.phonebook.dagger.modules.ViewModelsModule;
import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.views.ui.activities.AuthorisationActivity;
import com.example.phonebook.views.ui.fragments.LogInFragment;
import com.example.phonebook.views.ui.fragments.RegisterFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ViewModelsModule.class})
@ActivitiesScope
public interface AuthActViewModelsComponent {

    @Subcomponent.Builder
    interface Builder {
        AuthActViewModelsComponent build();
    }

    void inject(LogInFragment logInFragment);
    void inject(RegisterFragment registerFragment);
    void inject(AuthorisationActivity authorisationActivity);
}
