package com.example.phonebook.repos;

import java.util.Map;
import java.util.NoSuchElementException;

public interface UsersRepo {
    void addUser(String _userLogin, String _userPassword) throws IllegalArgumentException;
    void setSignedUp(Boolean _flag);
    boolean verifyUser(String _user, String _userPassword);
    void setCurrentUser(String _currentUser);
    void deleteUser(String _userLogin) throws NoSuchElementException;
    void updateUser(String _currentUser, String _newUserPassword) throws NoSuchElementException;
    Map <String, ?> getAllUsers();
}
