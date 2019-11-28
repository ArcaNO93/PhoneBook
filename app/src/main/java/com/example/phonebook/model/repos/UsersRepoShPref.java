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
    public void addUser(@NonNull String userLogin, @NonNull String userPassword) throws IllegalArgumentException{
        if(mUsers.contains(userLogin)) {
            throw new IllegalArgumentException(USER_EXISTS_ERROR);
        }
        mUsers.edit().putString(userLogin, userPassword).apply();
    }

    @Override
    public void deleteUser(@NonNull String userLogin) throws NoSuchElementException {
        if(mUsers.contains(userLogin)) {
            mUsers.edit().remove(userLogin).apply();
        }
        else throw new NoSuchElementException(NO_USER_ERROR);
    }

    @Override
    public void updateUser(@NonNull String currentUser, @NonNull String newUserPassword) throws NoSuchElementException {
        if(mUsers.contains(currentUser)) {
            mUsers.edit().putString(currentUser, newUserPassword).apply();
        }
        else throw new NoSuchElementException(NO_USER_ERROR);
    }

    @Override
    public Map<String, ?> getAllUsers() {
        return mUsers.getAll();
    }

    @Override
    public boolean verifyUser(@NonNull String user, @NonNull String userPassword) {
        return mUsers.getString(user, "").equals(userPassword);
    }

}
