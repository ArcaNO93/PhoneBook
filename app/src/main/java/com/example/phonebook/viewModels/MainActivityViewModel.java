package com.example.phonebook.viewModels;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.repos.ContactsRepoShPref;
import com.example.phonebook.model.repos.ServiceRepoShPref;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    @Inject
    ContactsRepoShPref mContactRepo;

    @Inject
    ServiceRepoShPref mService;

    @Inject
    Contact mContact;

    @Inject
    Application application;

    private ArrayList<Contact> mCurrentContactList = new ArrayList<>();
    private MutableLiveData<ArrayList<Contact>> mContacts = new MutableLiveData<>();

    public MainActivityViewModel() {
        ComponentProvider.getInstance().addActivityComponent().inject(this);
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
        mCurrentContactList.addAll(mContactRepo.getAllContacts(mService.getCurrentUser()));
        mContacts.postValue(mCurrentContactList);
    }

    public boolean addContact() {
        if(mContact.getContactName().length() == 0) {
            Toast mErrorNoName = Toast.makeText(application, "Contact is empty", Toast.LENGTH_SHORT);
            mErrorNoName.show();
            return false;
        }

        Contact contact = new Contact();

        contact.setContactName(mContact.getContactName());
        contact.setContactPhone(mContact.getContactPhone());
        contact.setContactEmail(mContact.getContactEmail());
        contact.setContactAddress(mContact.getContactAddress());

        int index = mCurrentContactList.indexOf(mContact);

        if(index < 0)
            mCurrentContactList.add(contact);
        else
            mCurrentContactList.set(index, contact);

        mContacts.postValue(mCurrentContactList);
        mContact.clean();

        return true;
    }

    public void deleteContact() {
        mCurrentContactList.remove(mContact);
        mContacts.postValue(mCurrentContactList);
        mContact.clean();
    }

    public void signOut() {
        mService.setSignedUp(false);
    }

    public void save() {
        mContactRepo.saveContactList(mService.getCurrentUser(), mCurrentContactList);
    }
}
