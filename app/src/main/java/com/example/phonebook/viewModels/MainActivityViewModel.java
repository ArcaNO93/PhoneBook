package com.example.phonebook.viewModels;

import android.app.Application;

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

    private ArrayList<Contact> mCurrentContactList = new ArrayList<>();
    private MutableLiveData<ArrayList<Contact>> mContacts = new MutableLiveData<>();

    public MainActivityViewModel(@NonNull Application _application) {
        super(_application);
        mContactRepo = new ContactsRepoShPref(_application);
        mService = new ServiceRepoShPref(_application);
    }

    public MutableLiveData<ArrayList<Contact>> getCurrentContactList() {
        return mContacts;
    }

    public void init() {
        mCurrentContactList.addAll(mContactRepo.getAllContacts(mService.getCurrentUser()));
        mContacts.postValue(mCurrentContactList);
    }

    public void addContact(Contact _newContact) {
        mCurrentContactList.add(_newContact);
        mContacts.postValue(mCurrentContactList);
    }

    public void deleteContact(int _contactPosition) {
        mCurrentContactList.remove(_contactPosition);
        mContacts.postValue(mCurrentContactList);
    }

    public Contact getContact(int _contactPosition) {
        return mCurrentContactList.get(_contactPosition);
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


    public void signOut() {
        mService.setSignedUp(false);
    }

    public void save() {
        mContactRepo.saveContactList(mService.getCurrentUser(), mCurrentContactList);
    }
}
