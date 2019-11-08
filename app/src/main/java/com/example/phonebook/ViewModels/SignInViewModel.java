package com.example.phonebook.ViewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.repos.UsersRepoShPref;

public class SignInViewModel extends AndroidViewModel {

    private String mLogin;
    private String mPassword;
    private Context mContext;
    private UsersRepoShPref mRepo;
    private MutableLiveData<Boolean> isLogged = new MutableLiveData<>();
    private MutableLiveData<Boolean> toSignUp = new MutableLiveData<>(false);

    public SignInViewModel(@NonNull Application _application) {
        super(_application);
        mLogin = "";
        mPassword = "";
        mContext = _application.getApplicationContext();
        mRepo = new UsersRepoShPref(_application);
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
        } else if (mRepo.getAllUsers().get(mLogin) == null) {
            showToast(mContext, mContext.getString(R.string.error_account_does_not_exist));
        } else if (!(mRepo.verifyUser(mLogin, mPassword))) {
            showToast(mContext, mContext.getString(R.string.error_wrong_password));
        } else {
            mRepo.setCurrentUser(mLogin);
            mRepo.setSignedUp(true);
            isLogged.postValue(true);
        }
    }

    public void toSignUp () {
        toSignUp.postValue(true);
    }

    private void showToast(Context _context, String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(_context, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }

    public void init() {
        if (String.valueOf(mRepo.getAllUsers().get("Signed up")).equals("true"))
            isLogged.postValue(true);
    }
}
