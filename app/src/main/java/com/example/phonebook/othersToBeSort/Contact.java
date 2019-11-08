package com.example.phonebook.othersToBeSort;

import java.io.Serializable;
import java.util.HashMap;

public class Contact implements Serializable {

    private HashMap<String, String> attributes = new HashMap<>();

    Contact() {
        attributes.put("Name", "");
        attributes.put("Phone", "");
        attributes.put("Email", "");
        attributes.put("Address", "");
    }

    String getName() {
        return attributes.get("Name");
    }

    String getPhone() {
        return attributes.get("Phone");
    }

    String getEmail() {
        return attributes.get("Email");
    }

    String getAddress() {
        return attributes.get("Address");
    }

    void setName(String _name) {
        attributes.put("Name", _name);
    }

    void setPhone(String _phone) {
        attributes.put("Phone", _phone);
    }

    void setEmail(String _email) {
        attributes.put("Email", _email);
    }

    void setAddress(String _address) {
        attributes.put("Address", _address);
    }

}
