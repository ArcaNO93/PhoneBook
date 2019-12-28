package com.example.phonebook.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonebook.business.interactors.MainInteractorImpl
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.pojo.ContactView
import javax.inject.Inject

@ActivitiesScope
class ContactCreationViewModel
@Inject constructor(
        private val mMainInteractor: MainInteractorImpl) : ViewModel() {

    fun addContact(contact: ContactView) = mMainInteractor.addContact(contact)
}