package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

public class SignInActivityViewModel extends AndroidViewModel {

    private String mLogin;
    private String mPassword;
    private Context mContext;
    private UsersRepoShPref mUsersRepo;
    private ServiceRepoShPref mServiceRepo;
    private MutableLiveData<Boolean> isLogged = new MutableLiveData<>();
    private MutableLiveData<Boolean> toSignUp = new MutableLiveData<>(false);

    public SignInActivityViewModel(@NonNull Application _application) {
        super(_application);
        mLogin = "";
        mPassword = "";
        mContext = _application.getApplicationContext();
        mUsersRepo = new UsersRepoShPref(_application);
        mServiceRepo = new ServiceRepoShPref(_application);
    }

    public MutableLiveData<Boolean> getIsLogged() {
        return isLogged;
    }

    public MutableLiveData<Boolean> getToSignUp() {
        return toSignUp;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String _mLogin) {
        this.mLogin = _mLogin;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String _mPassword) {
        this.mPassword = _mPassword;
    }

    public void signIn() {
        if (mLogin.length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_login));
        } else if (mPassword.length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_password));
        } else if (mUsersRepo.getAllUsers().get(mLogin) == null) {
            showToast(mContext, mContext.getString(R.string.error_account_does_not_exist));
        } else if (!(mUsersRepo.verifyUser(mLogin, mPassword))) {
            showToast(mContext, mContext.getString(R.string.error_wrong_password));
        } else {
            mServiceRepo.setCurrentUser(mLogin);
            mServiceRepo.setSignedUp(true);
            isLogged.postValue(true);
        }
    }

    private void showToast(Context _context, String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(_context, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void toSignUp () {
        toSignUp.postValue(true);
    }

    public void init() {
        if (mServiceRepo.getSignedUp())
            isLogged.postValue(true);
    }

    public void clean() {
        if(!mLogin.isEmpty())
            mLogin = "";
        if(!mPassword.isEmpty())
            mPassword = "";
    }
}
