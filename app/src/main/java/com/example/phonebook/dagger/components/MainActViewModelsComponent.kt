package com.example.phonebook.dagger.components

import com.example.phonebook.dagger.modules.ViewModelsModule
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.views.ui.activities.MainActivity
import com.example.phonebook.views.ui.fragments.ConfirmDeletionFragment
import com.example.phonebook.views.ui.fragments.ContactCreationFragment
import com.example.phonebook.views.ui.fragments.ContactListFragment
import com.example.phonebook.views.ui.fragments.ViewContactFragment

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelsModule::class])
@ActivitiesScope
interface MainActViewModelsComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build(): MainActViewModelsComponent
    }

    fun inject(confirmDeletionFragment: ConfirmDeletionFragment)
    fun inject(contactCreationFragment: ContactCreationFragment)
    fun inject(contactListFragment: ContactListFragment)
    fun inject(viewContactFragment: ViewContactFragment)

    fun inject(mainActivity: MainActivity)
}
