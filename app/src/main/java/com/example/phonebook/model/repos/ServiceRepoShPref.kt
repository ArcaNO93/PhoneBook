package com.example.phonebook.model.repos

import android.content.SharedPreferences

import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.utils.ComponentProvider

import javax.inject.Inject

@ActivitiesScope
class ServiceRepoShPref @Inject constructor() : ServiceRepo {

    init {
        ComponentProvider.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var mService: SharedPreferences

    override fun setCurrentUser(currentUser: String) {
        mService.edit().putString("Current user", currentUser).apply()
    }

    override fun setSignedUp(flag: Boolean) {
        mService.edit().putBoolean("Signed up", flag).apply()
    }

    override fun getCurrentUser(): String? {
        return mService.getString("Current user", "")
    }

    override fun getSignedUp(): Boolean {
        return mService.getBoolean("Signed up", false)
    }
}
