package com.example.phonebook.ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.repos.UsersRepoShPref;

public class SignUpViewModel extends AndroidViewModel {
    private String mLogin;
    private String mPassword;
    private UsersRepoShPref mRepo;
    private Context mContext;
    private MutableLiveData<Boolean> isFinished = new MutableLiveData<>();

    public SignUpViewModel(@NonNull Application _application) {
        super(_application);
        mLogin = "";
        mPassword = "";
        mContext = _application.getApplicationContext();
        mRepo = new UsersRepoShPref(_application);
    }

    public MutableLiveData<Boolean> getIsFinished() {
        return isFinished;
    }

    public String getLogin() {
        return mLogin;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setLogin(String _mLogin) {
        this.mLogin = _mLogin;
    }

    public void setPassword(String _mPassword) {
        this.mPassword = _mPassword;
    }

    public void signUp() {
        if (mLogin.length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_login));
        } else if (mPassword.length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_password));
        } else {
            try {
                mRepo.addUser(mLogin, mPassword);
            } catch (IllegalArgumentException e) {
                showToast(mContext, mContext.getString(R.string.error_login_exists));
                return;
            }
            showToast(mContext, mContext.getString(R.string.user_creation_success));
            isFinished.setValue(true);
        }
    }

    private void showToast(Context _context, String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(_context, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }
}

