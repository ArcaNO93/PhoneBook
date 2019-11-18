package com.example.phonebook.viewModels;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.phonebook.model.data.Contact;

public class CreateContactFragmentViewModel extends AndroidViewModel {

    private Contact mContact;
    private Context mContext;
    private MutableLiveData<Contact> mLiveContact = new MutableLiveData<>();

    public CreateContactFragmentViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
        mContact = new Contact();
    }

    public Contact getContact() {
        return mContact;
    }

    public void setContact(Contact _contact) {
        this.mContact = _contact;
    }

    public MutableLiveData<Contact> getLiveContact() {
        return mLiveContact;
    }

    public void create() {
        if(mContact.getContactName().length() == 0) {
            Toast mErrorNoName = Toast.makeText(mContext, "Contact is empty", Toast.LENGTH_SHORT);
            mErrorNoName.show();
            return;
        }
        Contact contact = new Contact();
        contact.setContactName(mContact.getContactName());
        contact.setContactPhone(mContact.getContactPhone());
        contact.setContactEmail(mContact.getContactEmail());
        contact.setContactAddress(mContact.getContactAddress());
        mLiveContact.postValue(contact);
    }
}
