package com.example.phonebook.model.data;

import com.example.phonebook.dagger.scopes.ActivitiesScope;

import java.io.Serializable;
import java.util.UUID;

import javax.inject.Inject;

@ActivitiesScope
public class Contact implements Serializable {

    private String ID;
    private String contactName;
    private String contactPhone;
    private String contactEmail;
    private String contactAddress;

    @Inject
    public Contact() {
        ID = UUID.randomUUID().toString();
        contactName = "";
        contactPhone = "";
        contactEmail = "";
        contactAddress = "";
    }

    public String getID() {
        return ID;
    }

    public String getContactName() {
        return contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactName(String _name) {
        contactName = _name;
    }

    public void setContactPhone(String _phone) {
        contactPhone = _phone;
    }

    public void setContactEmail(String _email) {
        contactEmail = _email;
    }

    public void setContactAddress(String _address) {
        contactAddress = _address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return ID.equals(contact.ID);

    }

    public void clean() {
        contactName = "";
        contactPhone = "";
        contactEmail = "";
        contactAddress = "";
    }
}
