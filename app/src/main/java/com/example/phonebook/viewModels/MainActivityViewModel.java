package com.example.phonebook.viewModels;

import android.app.Application;

import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.repos.ContactsRepoByRoom;
import com.example.phonebook.model.repos.ServiceRepoShPref;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivityViewModel extends ViewModel {

    private ContactsRepoByRoom mContactRepo;
    private ServiceRepoShPref mServiceRepo;
    private Contact mContact;
    private Application mApplication;

    private ArrayList<Contact> mCurrentContactList;
    private MutableLiveData<ArrayList<Contact>> mContacts;

    @Inject
    public MainActivityViewModel(ContactsRepoByRoom contactsRepo, ServiceRepoShPref serviceRepo, Contact contact, Application application) {
        mContactRepo = contactsRepo;
        mServiceRepo = serviceRepo;
        mContact = contact;
        mApplication = application;
        mCurrentContactList = new ArrayList<>();
        mContacts = new MutableLiveData<>();
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
        if(mContactRepo.getContactList(mServiceRepo.getCurrentUser()) != null)
            mCurrentContactList.addAll(mContactRepo.getContactList(mServiceRepo.getCurrentUser()));
        mContacts.postValue(mCurrentContactList);
    }

    public boolean addContact() {
        if(mContact.getContactName().length() == 0) {
            Toast mErrorNoName = Toast.makeText(mApplication, "Contact is empty", Toast.LENGTH_SHORT);
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
        mServiceRepo.setSignedUp(false);
    }

    public void save() {
        mContactRepo.saveContactList(mServiceRepo.getCurrentUser(), mCurrentContactList);
    }
}
