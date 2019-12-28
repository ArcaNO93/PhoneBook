package com.example.phonebook.dagger.modules

import androidx.lifecycle.ViewModel
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.dagger.scopes.ViewModelKeys
import com.example.phonebook.ui.viewModels.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(AuthorisationActivityViewModel::class)
    internal abstract fun provideAuthorisationActivityViewModel(authorisationActivityViewModel: AuthorisationActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(ContactCreationViewModel::class)
    internal abstract fun provideContactCreationViewModel(contactCreationViewModel: ContactCreationViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(ContactListViewModel::class)
    internal abstract fun provideContactListViewModel(contactListViewModel: ContactListViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(LogInViewModel::class)
    internal abstract fun provideLogInViewModel(logInViewModel: LogInViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(MainActivityViewModel::class)
    internal abstract fun provideMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(RegisterViewModel::class)
    internal abstract fun provideRegisterViewModel(registerViewModel: RegisterViewModel): ViewModel

}