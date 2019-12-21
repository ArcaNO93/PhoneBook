package com.example.phonebook.dagger.components

import android.app.Application

import com.example.phonebook.dagger.modules.AppModule
import com.example.phonebook.dagger.scopes.GlobalScope
import com.example.phonebook.model.repos.ContactsRepoByRoom
import com.example.phonebook.model.repos.ServiceRepoShPref
import com.example.phonebook.model.repos.UsersRepoByRoom

import dagger.BindsInstance
import dagger.Component

@Component(modules = [AppModule::class])
@GlobalScope
interface AppComponent {
    fun authActViewModelsComponent(): AuthActViewModelsComponent.Builder
    fun mainActViewModelsComponent(): MainActViewModelsComponent.Builder

    @Component.Builder
    interface Builder {
        @BindsInstance fun withApplication(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(contactsRepo: ContactsRepoByRoom)
    fun inject(serviceRepo: ServiceRepoShPref )
    fun inject(usersRepo: UsersRepoByRoom )
}
