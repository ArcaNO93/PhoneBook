package com.example.phonebook.dagger.components

import com.example.phonebook.dagger.modules.ViewModelsModule
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.views.ui.activities.AuthorisationActivity
import com.example.phonebook.views.ui.fragments.LogInFragment
import com.example.phonebook.views.ui.fragments.RegisterFragment

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelsModule::class])
@ActivitiesScope
interface AuthActViewModelsComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): AuthActViewModelsComponent
    }

    fun inject(logInFragment: LogInFragment)
    fun inject(registerFragment: RegisterFragment)
    fun inject(authorisationActivity: AuthorisationActivity)
}
