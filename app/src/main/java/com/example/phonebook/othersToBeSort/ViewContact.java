package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;

public class ViewContact extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contact, container, false);

        //TextView mContactName = view.findViewById(R.id.contactViewNameTextBox);
        //TextView mContactPhone = view.findViewById(R.id.contactViewPhoneTextBox);
        //TextView mContactEmail = view.findViewById(R.id.contactVewEmailTextBox);
        //TextView mContactAddress = view.findViewById(R.id.contactViewAddressTextBox);

        Contact contact = (Contact) getActivity().getIntent().getSerializableExtra("ITEM");

        //mContactName.setText(contact.getName());
        //mContactPhone.setText(contact.getPhone());
        //mContactEmail.setText(contact.getEmail());
        //mContactAddress.setText(contact.getAddress());

        return view;
    }
}
