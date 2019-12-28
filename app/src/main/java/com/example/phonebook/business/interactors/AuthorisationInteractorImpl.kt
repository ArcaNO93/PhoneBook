package com.example.phonebook.business.interactors

import android.app.Application
import android.widget.Toast
import com.example.phonebook.R
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.User
import com.example.phonebook.data.repos.ServiceRepoShPref
import com.example.phonebook.data.repos.UsersRepoByRoom
import javax.inject.Inject

@ActivitiesScope
class AuthorisationInteractorImpl
@Inject constructor(
        private val mUsersRepo: UsersRepoByRoom,
        private val mServiceRepo: ServiceRepoShPref,
        private val mApplication: Application,
        var mUser: User) : AuthorisationInteractor {

    override fun register(): Boolean {
        when {
            mUser.login.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_login))
            mUser.password.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_password))
            mUsersRepo.getUser(mUser.login) != null -> showToast(mApplication.getString(R.string.error_login_exists))
            else -> {
                mUsersRepo.addUser(mUser)
                showToast(mApplication.getString(R.string.user_creation_success))
                return true
            }
        }
        return false
    }

    override fun validateData(): Boolean {
        when {
            mUser.login.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_login))

            mUser.password.isEmpty() -> showToast(mApplication.getString(R.string.error_empty_password))

            mUsersRepo.getUser(mUser.login) == null -> showToast(mApplication.getString(R.string.error_account_does_not_exist))

            mUsersRepo.getUser(mUser.login)?.password != mUser.password -> showToast(mApplication.getString(R.string.error_wrong_password))

            else -> {
                mServiceRepo.setCurrentUser(mUser.login)
                mServiceRepo.setSignedUp(true)
                return true
            }
        }
        return false
    }

    override fun init(): Boolean = mServiceRepo.getSignedUp()

    override fun userClean() {
        mUser.login = ""
        mUser.password = ""
    }

    private fun showToast(_errorMassage: String) = Toast.makeText(mApplication, _errorMassage, Toast.LENGTH_SHORT).show()
}