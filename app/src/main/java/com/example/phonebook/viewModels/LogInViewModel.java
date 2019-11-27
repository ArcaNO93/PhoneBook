package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.R;
import com.example.phonebook.model.data.User;
import com.example.phonebook.model.repos.ServiceRepoShPref;
import com.example.phonebook.model.repos.UsersRepoShPref;

public class LogInViewModel extends AndroidViewModel {

    private User mUser;
    private Context mContext;
    private UsersRepoShPref mUsersRepo;
    private ServiceRepoShPref mServiceRepo;

    public LogInViewModel(@NonNull Application _application) {
        super(_application);
        mUser = new User();
        mContext = _application.getApplicationContext();
        mUsersRepo = new UsersRepoShPref(_application);
        mServiceRepo = new ServiceRepoShPref(_application);
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public void signIn() {
        if (mUser.getLogin().length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_login));
        } else if (mUser.getPassword().length() == 0) {
            showToast(mContext, mContext.getString(R.string.error_empty_password));
        } else if (mUsersRepo.getAllUsers().get(mUser.getLogin()) == null) {
            showToast(mContext, mContext.getString(R.string.error_account_does_not_exist));
        } else if (!(mUsersRepo.verifyUser(mUser.getLogin(), mUser.getPassword()))) {
            showToast(mContext, mContext.getString(R.string.error_wrong_password));
        } else {
            mServiceRepo.setCurrentUser(mUser.getLogin());
            mServiceRepo.setSignedUp(true);
        }
    }

    private void showToast(Context _context, String _errorMassage) {
        Toast mErrorNoLogin = Toast.makeText(_context, _errorMassage, Toast.LENGTH_SHORT);
        mErrorNoLogin.show();
    }
}
