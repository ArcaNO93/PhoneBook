package com.example.phonebook.dagger.modules;

import androidx.lifecycle.ViewModel;

import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.dagger.scopes.ViewModelKeys;
import com.example.phonebook.viewModels.LogInViewModel;
import com.example.phonebook.viewModels.MainActivityViewModel;
import com.example.phonebook.viewModels.RegisterViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(LogInViewModel.class)
    public abstract ViewModel provideLogInViewModel(LogInViewModel logInViewModel);

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(MainActivityViewModel.class)
    public abstract ViewModel provideMainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ActivitiesScope
    @ViewModelKeys(RegisterViewModel.class)
    public abstract ViewModel provideRegisterViewModel(RegisterViewModel registerViewModel);
}
