package com.example.phonebook.model.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.phonebook.dagger.scopes.ActivitiesScope;

import java.util.UUID;

import javax.inject.Inject;

@Entity(tableName = "users")
@ActivitiesScope
public class User {

    @PrimaryKey
    @ColumnInfo(name = "user_id")
    @NonNull
    private String  ID;

    @ColumnInfo(name = "user_login")
    private String login;

    @ColumnInfo(name = "user_password")
    private String password;

    @Inject
    public User() {
        ID = UUID.randomUUID().toString();
        login = "";
        password = "";
    }

    public void setID(@NonNull String _ID) {
        this.ID = _ID;
    }

    @NonNull
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
