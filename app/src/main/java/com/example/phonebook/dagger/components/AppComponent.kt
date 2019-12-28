package com.example.phonebook.dagger.components

import android.app.Application

import com.example.phonebook.dagger.modules.AppModule
import com.example.phonebook.dagger.scopes.GlobalScope
import com.example.phonebook.data.repos.ContactsRepoByRoom
import com.example.phonebook.data.repos.ServiceRepoShPref
import com.example.phonebook.data.repos.UsersRepoByRoom

import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@GlobalScope
interface AppComponent {
    fun authComponent(): AuthComponent.Builder
    fun mainComponent(): MainComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(contactsRepo: ContactsRepoByRoom)
    fun inject(serviceRepo: ServiceRepoShPref)
    fun inject(usersRepo: UsersRepoByRoom)
}
