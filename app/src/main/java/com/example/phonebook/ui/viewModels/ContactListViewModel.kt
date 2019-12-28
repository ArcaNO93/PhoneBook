package com.example.phonebook.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonebook.business.interactors.MainInteractorImpl
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.pojo.ContactView
import javax.inject.Inject

@ActivitiesScope
class ContactListViewModel
@Inject constructor(
        var mContact: ContactView,
        private val mMainInteractor: MainInteractorImpl) : ViewModel() {
    fun getContactList() = mMainInteractor.getContactList()

    fun updateContact() = mMainInteractor.updateContact(mContact)

    fun deleteContact() = mMainInteractor.deleteContact(mContact)

}