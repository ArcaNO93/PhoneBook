package com.example.phonebook.model.repos;

import com.example.phonebook.model.data.User;

public interface UsersRepo {

    long addUser(User user);
    void deleteUser(User user);
    void updateUser(String ID, User newUser);
    User getUser(String login);

}
