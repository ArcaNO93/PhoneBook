package com.example.phonebook.model.repos;

import androidx.annotation.NonNull;

import java.util.Map;
import java.util.NoSuchElementException;

public interface UsersRepo {
    void addUser(@NonNull String userLogin, @NonNull String userPassword) throws IllegalArgumentException;
    boolean verifyUser(@NonNull String user, @NonNull String userPassword);
    void deleteUser(@NonNull String userLogin) throws NoSuchElementException;
    void updateUser(@NonNull String currentUser, @NonNull String newUserPassword) throws NoSuchElementException;
    Map <String, ?> getAllUsers();
}
