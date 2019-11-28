package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.UsersRepoShPref;

public class RegisterViewModel extends AndroidViewModel {

    private User mUser;
    private UsersRepoShPref mRepo;
    private Context mContext;
    private MutableLiveData<Boolean> mRegistrationDone = new MutableLiveData<>();

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        mUser = new User();
        mContext = application.getApplicationContext();
        mRepo = new UsersRepoShPref(application);
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
            showToast(mContext, mContext.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_password));
        } else {
            try {
                mRepo.addUser(mUser.getLogin(), mUser.getPassword());
            } catch (IllegalArgumentException e) {
                showToast(mContext, mContext.getString(R.string.error_login_exists));
                return;
            }
            showToast(mContext, mContext.getString(R.string.user_creation_success));
            mRegistrationDone.setValue(true);
        }
    }

    private void showToast(Context _context, String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(_context, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }
}
