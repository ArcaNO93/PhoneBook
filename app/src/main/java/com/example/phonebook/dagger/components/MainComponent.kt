package com.example.phonebook.dagger.components

import com.example.phonebook.dagger.modules.ViewModelsModule
import com.example.phonebook.ui.fragments.ContactEditFragment
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.ui.activities.MainActivity
import com.example.phonebook.ui.fragments.ContactDeletionFragment
import com.example.phonebook.ui.fragments.ContactCreationFragment
import com.example.phonebook.ui.fragments.ContactListFragment
import com.example.phonebook.ui.fragments.ViewContactFragment

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelsModule::class])
@ActivitiesScope
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainComponent
    }

    fun inject(confirmDeletionFragment: ContactDeletionFragment)
    fun inject(contactCreationFragment: ContactCreationFragment)
    fun inject(contactListFragment: ContactListFragment)
    fun inject(viewContactFragment: ViewContactFragment)
    fun inject(contactEditFragment: ContactEditFragment)

    fun inject(mainActivity: MainActivity)
}
