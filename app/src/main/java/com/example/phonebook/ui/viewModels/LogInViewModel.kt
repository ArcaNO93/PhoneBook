package com.example.phonebook.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.phonebook.business.interactors.AuthorisationInteractorImpl
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.User

import javax.inject.Inject

@ActivitiesScope
class LogInViewModel
@Inject constructor(
        var mUser: User,
        private val mAuthorisationInteractor: AuthorisationInteractorImpl) : ViewModel() {

    var isLogged: MutableLiveData<Boolean> = MutableLiveData()

    fun logIn() = isLogged.postValue(mAuthorisationInteractor.validateData())

    fun clean() = mAuthorisationInteractor.userClean()
}
