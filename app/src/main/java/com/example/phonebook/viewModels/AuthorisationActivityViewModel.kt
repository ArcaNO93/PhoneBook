package com.example.phonebook.viewModels

import androidx.lifecycle.ViewModel
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.model.repos.ServiceRepoShPref
import javax.inject.Inject

@ActivitiesScope
class AuthorisationActivityViewModel
@Inject constructor(private var mServiceRepo: ServiceRepoShPref) : ViewModel() {
    fun init() = mServiceRepo.getSignedUp()
}