package com.example.phonebook.dagger.components;

import com.example.phonebook.dagger.modules.ViewModelsModule;
import com.example.phonebook.dagger.scopes.ActivitiesScope;
import com.example.phonebook.views.ui.activities.MainActivity;
import com.example.phonebook.views.ui.fragments.ConfirmDeletionFragment;
import com.example.phonebook.views.ui.fragments.ContactCreationFragment;
import com.example.phonebook.views.ui.fragments.ContactListFragment;
import com.example.phonebook.views.ui.fragments.ViewContactFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {ViewModelsModule.class})
@ActivitiesScope
public interface MainActViewModelsComponent {

    @Subcomponent.Builder
    interface Builder {
        MainActViewModelsComponent build();
    }

    void inject(ConfirmDeletionFragment confirmDeletionFragment);
    void inject(ContactCreationFragment contactCreationFragment);
    void inject(ContactListFragment contactListFragment);
    void inject(ViewContactFragment viewContactFragment);

    void inject(MainActivity mainActivity);
}
