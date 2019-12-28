package com.example.phonebook.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonebook.business.interactors.MainInteractorImpl
import com.example.phonebook.dagger.scopes.ActivitiesScope

import com.example.phonebook.data.pojo.ContactView

import javax.inject.Inject

@ActivitiesScope
class MainActivityViewModel
@Inject constructor(
        val mMainInteractor: MainInteractorImpl): ViewModel() {

    fun logOut() = mMainInteractor.logOut()

    fun save() = mMainInteractor.save()
}
