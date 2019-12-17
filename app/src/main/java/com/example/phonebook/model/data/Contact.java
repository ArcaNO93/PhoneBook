package com.example.phonebook.model.data;

import com.example.phonebook.dagger.scopes.ActivitiesScope;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import javax.inject.Inject;

@ActivitiesScope
public class Contact implements Serializable {

    private String ID;
    private HashMap<String, String> attributes = new HashMap<>();

    @Inject
    public Contact() {
        ID = UUID.randomUUID().toString();
        attributes.put("Name", "");
        attributes.put("Phone", "");
        attributes.put("Email", "");
        attributes.put("Address", "");
        attributes.put("ID", ID);
    }

    public String getID() {
        return ID;
    }

    public String getContactName() {
        return attributes.get("Name");
    }

    public String getContactPhone() {
        return attributes.get("Phone");
    }

    public String getContactEmail() {
        return attributes.get("Email");
    }

    public String getContactAddress() {
        return attributes.get("Address");
    }

    public void setContactName(String _name) {
        attributes.put("Name", _name);
    }

    public void setContactPhone(String _phone) {
        attributes.put("Phone", _phone);
    }

    public void setContactEmail(String _email) {
        attributes.put("Email", _email);
    }

    public void setContactAddress(String _address) {
        attributes.put("Address", _address);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        if (ID.equals(contact.ID))
            return false;
        return attributes.equals(contact.attributes);
    }

    public void clean() {
        attributes.put("Name", "");
        attributes.put("Phone", "");
        attributes.put("Email", "");
        attributes.put("Address", "");
    }
}
