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
import com.example.phonebook.model.repos.UsersRepoShPref;

import javax.inject.Inject;

public class RegisterViewModel extends AndroidViewModel {

    @Inject
    User mUser;

    @Inject
    UsersRepoShPref mRepo;

    private MutableLiveData<Boolean> mRegistrationDone = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
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
        Context app = getApplication().getApplicationContext();
        if (mUser.getLogin().length() == 0) {
            showToast(app.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(app.getString(R.string.error_empty_password));
        } else {
            try {
                mRepo.addUser(mUser.getLogin(), mUser.getPassword());
            } catch (IllegalArgumentException e) {
                showToast(app.getString(R.string.error_login_exists));
                return;
            }
            showToast(app.getString(R.string.user_creation_success));
            mRegistrationDone.setValue(true);
        }
    }

    private void showToast(String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(getApplication().getApplicationContext(), _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void clean() {
        mUser.clean();
    }
}
