package com.example.phonebook.viewModels

import android.app.Application
import android.widget.Toast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.phonebook.R
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.model.data.User
import com.example.phonebook.model.repos.ServiceRepoShPref
import com.example.phonebook.model.repos.UsersRepoByRoom

import javax.inject.Inject

@ActivitiesScope
class LogInViewModel
@Inject constructor(
        var mUser: User,
        private var mUsersRepo: UsersRepoByRoom,
        private var mServiceRepo: ServiceRepoShPref,
        private var mApplication: Application) : ViewModel() {

    var isLogged: MutableLiveData<Boolean> = MutableLiveData()

    fun signIn() {
        when {
            mUser.login.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_login))

            mUser.password.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_password))

            mUsersRepo.getUser(mUser.login) == null -> showToast(mApplication.getString(R.string.error_account_does_not_exist))

            mUsersRepo.getUser(mUser.login)?.password != mUser.password -> showToast(mApplication.getString(R.string.error_wrong_password))

            else -> {
                mServiceRepo.setCurrentUser(mUser.login)
                mServiceRepo.setSignedUp(true)
                isLogged.postValue(true)
            }
        }
    }

    private fun showToast(errorMassage: String) = Toast.makeText(mApplication, errorMassage, Toast.LENGTH_SHORT).show()

    fun clean() = mUser.clean()
}
