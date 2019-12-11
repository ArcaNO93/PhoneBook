package com.example.phonebook.viewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.R;
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.UsersRepoShPref;

import javax.inject.Inject;

public class RegisterViewModel extends ViewModel {

    @Inject
    User mUser;

    @Inject
    UsersRepoShPref mRepo;

    @Inject
    Application application;

    private MutableLiveData<Boolean> mRegistrationDone = new MutableLiveData<>();

    public RegisterViewModel() {
        ComponentProvider.getInstance().addActivityComponent().inject(this);
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
            showToast(application.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(application.getString(R.string.error_empty_password));
        } else {
            try {
                mRepo.addUser(mUser.getLogin(), mUser.getPassword());
            } catch (IllegalArgumentException e) {
                showToast(application.getString(R.string.error_login_exists));
                return;
            }
            showToast(application.getString(R.string.user_creation_success));
            mRegistrationDone.setValue(true);
        }
    }

    private void showToast(String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(application, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void clean() {
        mUser.clean();
    }
}
