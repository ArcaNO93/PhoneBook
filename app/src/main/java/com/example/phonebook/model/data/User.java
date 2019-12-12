package com.example.phonebook.model.data;

import com.example.phonebook.dagger.scopes.ActivitiesScope;

import java.util.UUID;

import javax.inject.Inject;

@ActivitiesScope
public class User {
    private String  ID;
    private String login;
    private String password;

    @Inject
    public User() {
        ID = UUID.randomUUID().toString();
        login = "";
        password = "";
    }

    public String getID() {
        return ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String _login) {
        this.login = _login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public void clean() {
        login = "";
        password = "";
    }
}
