package com.example.phonebook.ui.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonebook.business.interactors.AuthorisationInteractorImpl
import com.example.phonebook.dagger.scopes.ActivitiesScope
import javax.inject.Inject

@ActivitiesScope
class AuthorisationActivityViewModel
@Inject constructor(
        private var mAuthorisationInteractor: AuthorisationInteractorImpl) : ViewModel() {

    fun init() = mAuthorisationInteractor.init()
}