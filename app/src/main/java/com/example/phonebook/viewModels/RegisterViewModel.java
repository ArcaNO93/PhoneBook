package com.example.phonebook.viewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.R;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.UsersRepoByRoom;


import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    private User mUser;
    private UsersRepoByRoom mUsersRepo;
    private Application mApplication;
    private MutableLiveData<Boolean> mRegistrationDone;

    @Inject
    public RegisterViewModel(User user, UsersRepoByRoom usersRepo, Application application) {
        mUser = user;
        mUsersRepo = usersRepo;
        mApplication = application;
        mRegistrationDone = new MutableLiveData<>();
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
            return;
        } else if (mUser.getPassword().length() == 0) {
            showToast(mApplication.getString(R.string.error_empty_password));
            return;
        } else if(mUsersRepo.getUser(mUser.getLogin()) != null) {
            showToast(mApplication.getString(R.string.error_login_exists));
            return;
        }
        mUsersRepo.addUser(mUser);
        showToast(mApplication.getString(R.string.user_creation_success));
        mRegistrationDone.postValue(true);
    }

    private void showToast(String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(mApplication, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void clean() {
        mUser.clean();
    }
}
