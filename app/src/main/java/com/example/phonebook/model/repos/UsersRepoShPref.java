package com.example.phonebook.model.repos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.NoSuchElementException;

public class UsersRepoShPref implements UsersRepo {

    private static final String NO_USER_ERROR = "Can't find user";
    private static final String USER_EXISTS_ERROR = "User already exists";

    private SharedPreferences mUsers;

    public UsersRepoShPref(@NonNull Application application){
        Context context = application.getApplicationContext();
        mUsers = context.getSharedPreferences("Users", Context.MODE_PRIVATE);
    }

    @Override
    public void addUser(String _userLogin, String _userPassword) throws IllegalArgumentException{
        if(mUsers.contains(_userLogin)) {
            throw new IllegalArgumentException(USER_EXISTS_ERROR);
        }
        mUsers.edit().putString(_userLogin, _userPassword).apply();
    }

    @Override
    public void deleteUser(String _userLogin) throws NoSuchElementException {
        if(mUsers.contains(_userLogin)) {
            mUsers.edit().remove(_userLogin).apply();
        }
        else throw new NoSuchElementException(NO_USER_ERROR);
    }

    @Override
    public void updateUser(String _currentUser, String _newUserPassword) throws NoSuchElementException {
        if(mUsers.contains(_currentUser)) {
            mUsers.edit().putString(_currentUser, _newUserPassword).apply();
        }
        else throw new NoSuchElementException(NO_USER_ERROR);
    }

    @Override
    public Map<String, ?> getAllUsers() {
        return mUsers.getAll();
    }

    @Override
    public boolean verifyUser(String _user, String _userPassword) {
        return mUsers.getString(_user, "").equals(_userPassword);
    }

}
