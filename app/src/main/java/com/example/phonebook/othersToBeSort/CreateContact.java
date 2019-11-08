package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.phonebook.R;

public class CreateContact extends Fragment {

    private EditText mContactName;
    private EditText mContactPhone;
    private EditText mContactEmail;
    private EditText mContactAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_creation, container, false);

        mContactName = view.findViewById(R.id.contactCreateNameEnterBox);
        mContactPhone = view.findViewById(R.id.contactCreatePhoneEnterBox);
        mContactEmail = view.findViewById(R.id.contactCreateEmailEnterBox);
        mContactAddress = view.findViewById(R.id.contactCreateAddressEnterBox);

        return view;
    }

    String getContactName() {
        return mContactName.getText().toString();
    }

    String getContactPhone() {
        return mContactPhone.getText().toString();
    }

    String getContactEmail() {
        return mContactEmail.getText().toString();
    }

    String getContactAddress() {
        return mContactAddress.getText().toString();
    }

}
