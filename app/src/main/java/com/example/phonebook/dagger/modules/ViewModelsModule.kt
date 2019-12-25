package com.example.phonebook.dagger.modules

import androidx.lifecycle.ViewModel

import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.dagger.scopes.ViewModelKeys
import com.example.phonebook.viewModels.LogInViewModel
import com.example.phonebook.viewModels.MainActivityViewModel
import com.example.phonebook.viewModels.RegisterViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelsModule {

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
    internal abstract fun provideRegisterViewModel(registerViewModel: RegisterViewModel ): ViewModel
}
