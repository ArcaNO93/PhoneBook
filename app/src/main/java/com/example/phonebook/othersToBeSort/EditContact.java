package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.phonebook.R;

public class EditContact extends Fragment {

    private EditText mContactName;
    private EditText mContactPhone;
    private EditText mContactEmail;
    private EditText mContactAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_contact, container, false);

        mContactName = view.findViewById(R.id.contactEditNameEnterBox);
        mContactPhone = view.findViewById(R.id.contactEditPhoneEnterBox);
        mContactEmail = view.findViewById(R.id.contactEditEmailEnterBox);
        mContactAddress = view.findViewById(R.id.contactEditAddressEnterBox);

        Contact contact = (Contact) getActivity().getIntent().getSerializableExtra("ITEM");

        mContactName.setText(contact.getName());
        mContactPhone.setText(contact.getPhone());
        mContactEmail.setText(contact.getEmail());
        mContactAddress.setText(contact.getAddress());

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