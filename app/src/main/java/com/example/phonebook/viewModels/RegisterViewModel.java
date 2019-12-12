package com.example.phonebook.viewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.R;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.UsersRepoShPref;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private User mUser;
    private UsersRepoShPref mUsersRepo;
    private Application mApplication;
    private MutableLiveData<Boolean> mRegistrationDone = new MutableLiveData<>();

    @Inject
    public RegisterViewModel(User user, UsersRepoShPref usersRepo, Application application) {
        mUser = user;
        mUsersRepo = usersRepo;
        mApplication = application;
    }

    public MutableLiveData<Boolean> getRegistrationDone() {
        return mRegistrationDone;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User _user) {
        mUser = _user;
    }

    public void signUp() {
        if (mUser.getLogin().length() == 0) {
            showToast(mApplication.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(mApplication.getString(R.string.error_empty_password));
        } else {
            try {
                mUsersRepo.addUser(mUser.getLogin(), mUser.getPassword());
            } catch (IllegalArgumentException e) {
                showToast(mApplication.getString(R.string.error_login_exists));
                return;
            }
            showToast(mApplication.getString(R.string.user_creation_success));
            mRegistrationDone.setValue(true);
        }
    }

    private void showToast(String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(mApplication, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void clean() {
        mUser.clean();
    }
}
