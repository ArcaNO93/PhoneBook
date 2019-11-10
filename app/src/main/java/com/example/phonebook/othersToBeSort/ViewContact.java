package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phonebook.R;

public class ViewContact extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contact, container, false);

        TextView mContactName = view.findViewById(R.id.contactViewNameTextBox);
        TextView mContactPhone = view.findViewById(R.id.contactViewPhoneTextBox);
        TextView mContactEmail = view.findViewById(R.id.contactVewEmailTextBox);
        TextView mContactAddress = view.findViewById(R.id.contactViewAddressTextBox);

        Contact contact = (Contact) getActivity().getIntent().getSerializableExtra("ITEM");

        mContactName.setText(contact.getContactName());
        mContactPhone.setText(contact.getContactPhone());
        mContactEmail.setText(contact.getContactEmail());
        mContactAddress.setText(contact.getContactAddress());

        return view;
    }
}
