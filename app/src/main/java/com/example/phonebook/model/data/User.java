package com.example.phonebook.model.data;

import com.example.phonebook.dagger.scopes.ActivitiesScope;

import javax.inject.Inject;

@ActivitiesScope
public class User {
    private String login;
    private String password;

    @Inject
    public User() {
        login = "";
        password = "";
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
