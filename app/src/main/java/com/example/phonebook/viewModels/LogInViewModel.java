package com.example.phonebook.viewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.R;
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

import javax.inject.Inject;

public class LogInViewModel extends ViewModel {

    @Inject
    User mUser;

    @Inject
    UsersRepoShPref mUsersRepo;

    @Inject
    ServiceRepoShPref mServiceRepo;

    @Inject
    Application application;

    private MutableLiveData<Boolean> isLogged = new MutableLiveData<>();

    public LogInViewModel() {
        ComponentProvider.getInstance().addActivityComponent().inject(this);
    }

    public User getUser() {
        return mUser;
    }

    public MutableLiveData<Boolean> getIsLogged() {
        return isLogged;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public void signIn() {
        if (mUser.getLogin().length() == 0) {
            showToast(application.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(application.getString(R.string.error_empty_password));
        } else if (mUsersRepo.getAllUsers().get(mUser.getLogin()) == null) {
            showToast(application.getString(R.string.error_account_does_not_exist));
        } else if (!(mUsersRepo.verifyUser(mUser.getLogin(), mUser.getPassword()))) {
            showToast(application.getString(R.string.error_wrong_password));
        } else {
            mServiceRepo.setCurrentUser(mUser.getLogin());
            mServiceRepo.setSignedUp(true);
            isLogged.postValue(true);
        }
    }

    public boolean init() {
        return mServiceRepo.getSignedUp();
    }

    private void showToast(String errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(application, errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }


    public void clean() {
        mUser.clean();
    }
}
