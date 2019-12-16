package com.example.phonebook.model.repos;

import androidx.annotation.Nullable;

import com.example.phonebook.model.data.User;
import com.example.phonebook.model.db.UsersDB;
import com.example.phonebook.utils.ComponentProvider;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

import javax.inject.Inject;

public class UsersRepoByRoom implements UsersRepo {

    @Inject
    UsersDB usersDB;

    @Inject
    public UsersRepoByRoom() {
        ComponentProvider.getInstance().getAppComponent().inject(this);
    }

    @Override
    public long addUser(User user) {
        Callable<Long> addUser = () -> usersDB.getUsersDB().insertUser(user);

        try {
            return Executors.newSingleThreadExecutor().submit(addUser).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void deleteUser(User user) {
        //TODO right now not needed
    }

    @Override
    public void updateUser(String ID, User newUser) {
        //TODO right now not needed
    }

    @Nullable
    @Override
    public User getUser(String login) {
        Callable<User> getUser = () -> usersDB.getUsersDB().getUser(login);

        try {
            return Executors.newSingleThreadExecutor().submit(getUser).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

}
