package com.example.phonebook.viewModels

import android.app.Application
import android.widget.Toast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.phonebook.R
import com.example.phonebook.model.data.User
import com.example.phonebook.model.repos.UsersRepoByRoom

import javax.inject.Inject

class RegisterViewModel @Inject constructor(
        var mUser: User,
        private var mUsersRepo: UsersRepoByRoom,
        private var mApplication: Application) : ViewModel() {

    var mRegistrationDone: MutableLiveData<Boolean> = MutableLiveData()

    fun signUp() {
        when {
            mUser.login.isEmpty() -> {
                showToast(mApplication.getString(R.string.error_empty_login))
            }
            mUser.password.isEmpty() -> {
                showToast(mApplication.getString(R.string.error_empty_password))
            }
            mUsersRepo.getUser(mUser.login) != null -> {
                showToast(mApplication.getString(R.string.error_login_exists))
            }
            else -> {
                mUsersRepo.addUser(mUser)
                showToast(mApplication.getString(R.string.user_creation_success))
                mRegistrationDone.postValue(true)
            }
        }
    }

    private fun showToast(_errorMassage: String) = Toast.makeText(mApplication, _errorMassage, Toast.LENGTH_SHORT).show()

    fun clean() = mUser.clean()
}
