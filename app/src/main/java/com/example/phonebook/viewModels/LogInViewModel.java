package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.dagger.ComponentProvider;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

import javax.inject.Inject;

public class LogInViewModel extends AndroidViewModel {

    @Inject
    User mUser;

    @Inject
    UsersRepoShPref mUsersRepo;

    @Inject
    ServiceRepoShPref mServiceRepo;

    private MutableLiveData<Boolean> isLogged = new MutableLiveData<>();

    public LogInViewModel(@NonNull Application application) {
        super(application);
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
        Context app = getApplication().getApplicationContext();
        if (mUser.getLogin().length() == 0) {
            showToast(app.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(app.getString(R.string.error_empty_password));
        } else if (mUsersRepo.getAllUsers().get(mUser.getLogin()) == null) {
            showToast(app.getString(R.string.error_account_does_not_exist));
        } else if (!(mUsersRepo.verifyUser(mUser.getLogin(), mUser.getPassword()))) {
            showToast(app.getString(R.string.error_wrong_password));
        } else {
            mServiceRepo.setCurrentUser(mUser.getLogin());
            mServiceRepo.setSignedUp(true);
            isLogged.postValue(true);
        }
    }

    private void showToast(String errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(getApplication().getApplicationContext(), errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void clean() {
        mUser.clean();
    }
}
