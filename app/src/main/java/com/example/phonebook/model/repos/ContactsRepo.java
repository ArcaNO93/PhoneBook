package com.example.phonebook.model.repos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.phonebook.model.data.Contact;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface ContactsRepo {
    ArrayList<Contact> getContactList(@NonNull String currentUser);
    long saveContactList(@NonNull String user, @Nullable ArrayList<Contact> contactList);

    default String stringFromObject(List<Contact> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    default ArrayList<Contact> objectFromString(String list) {
        Log.d("str", list);
        Type listType = new TypeToken<ArrayList<Contact>>(){}.getType();
        return new Gson().fromJson(list, listType);
    }
}
