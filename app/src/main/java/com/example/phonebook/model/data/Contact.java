package com.example.phonebook.model.data;

import java.io.Serializable;
import java.util.HashMap;

public class Contact implements Serializable {

    private static int count = 0;
    private int ID;
    private String contactName = "";
    private String contactPhone = "";
    private String contactEmail = "";
    private String contactAddress = "";

    private HashMap<String, String> attributes = new HashMap<>();

    public Contact() {
        attributes.put("Name", "");
        attributes.put("Phone", "");
        attributes.put("Email", "");
        attributes.put("Address", "");
        ID = count++;
    }

    public int getID() {
        return ID;
    }

    public String getContactName() {
        contactName = attributes.get("Name");
        return contactName;
    }

    public String getContactPhone() {
        contactPhone = attributes.get("Phone");
        return contactPhone;
    }

    public String getContactEmail() {
        contactEmail = attributes.get("Email");
        return contactEmail;
    }

    public String getContactAddress() {
        contactAddress = attributes.get("Address");
        return contactAddress;
    }

    public void setContactName(String _name) {
        this.contactName = _name;
        attributes.put("Name", _name);
    }

    public void setContactPhone(String _phone) {
        this.contactPhone = _phone;
        attributes.put("Phone", _phone);
    }

    public void setContactEmail(String _email) {
        this.contactEmail = _email;
        attributes.put("Email", _email);
    }

    public void setContactAddress(String _address) {
        this.contactAddress = _address;
        attributes.put("Address", _address);
    }

    @org.jetbrains.annotations.Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return ID == contact.ID &&
                contactName.equals(contact.contactName) &&
                contactPhone.equals(contact.contactPhone);
    }
}
