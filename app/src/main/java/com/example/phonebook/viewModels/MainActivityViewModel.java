package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.repos.ContactsRepoShPref;
import com.example.phonebook.model.repos.ServiceRepoShPref;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    private ContactsRepoShPref mContactRepo;
    private ServiceRepoShPref mService;
    private Contact mContact;
    private Context mContext;

    private ArrayList<Contact> mCurrentContactList = new ArrayList<>();
    private MutableLiveData<ArrayList<Contact>> mContacts = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application _application) {
        super(_application);
        mContactRepo = new ContactsRepoShPref(_application);
        mService = new ServiceRepoShPref(_application);
        mContact = new Contact();
        mContext = _application.getApplicationContext();
    }

    public Contact getContact() {
        return mContact;
    }

    public void setContact(Contact _contact) {
        this.mContact = _contact;
    }

    public MutableLiveData<ArrayList<Contact>> getCurrentContactList() {
        return mContacts;
    }

    public void init() {
        if(mCurrentContactList.isEmpty()) {
            mCurrentContactList.addAll(mContactRepo.getAllContacts(mService.getCurrentUser()));
            mContacts.postValue(mCurrentContactList);
        }
    }

    public boolean addContact() {
        if(mContact.getContactName().length() == 0) {
            Toast mErrorNoName = Toast.makeText(mContext, "Contact is empty", Toast.LENGTH_SHORT);
            mErrorNoName.show();
            return false;
        }
        Contact contact = new Contact();
        contact.setContactName(mContact.getContactName());
        contact.setContactPhone(mContact.getContactPhone());
        contact.setContactEmail(mContact.getContactEmail());
        contact.setContactAddress(mContact.getContactAddress());
        mCurrentContactList.add(contact);
        mContacts.postValue(mCurrentContactList);
        mContact.clean();

        return true;
    }

    public void deleteContact() {
        mCurrentContactList.remove(mContact);
        mContacts.postValue(mCurrentContactList);
        mContact.clean();
    }

    public void updateContact(int _contactPosition, String _newName, String _newPone, String _newEmail, String _newAddress) {
        Contact contact = mCurrentContactList.get(_contactPosition);
        contact.setContactName(_newName);
        contact.setContactPhone(_newPone);
        contact.setContactEmail(_newEmail);
        contact.setContactAddress(_newAddress);
        mCurrentContactList.set(_contactPosition, contact);
        mContacts.setValue(mCurrentContactList);
    }

    public void edit(Contact _contact) {
        mContact = _contact;
        if(mContact.getContactName().length() == 0) {
            Toast mErrorNoName = Toast.makeText(mContext, "Contact is empty", Toast.LENGTH_SHORT);
            mErrorNoName.show();
        }
    }

    public void signOut() {
        mService.setSignedUp(false);
    }

    public void save() {
        mContactRepo.saveContactList(mService.getCurrentUser(), mCurrentContactList);
    }
}
