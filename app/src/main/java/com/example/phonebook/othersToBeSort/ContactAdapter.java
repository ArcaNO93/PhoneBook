package com.example.phonebook.othersToBeSort;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.phonebook.R;

import java.util.ArrayList;
import java.util.Objects;

public class ContactAdapter extends ArrayAdapter<Contact> {

    ContactAdapter(Activity context, ArrayList<Contact> contact) {
        super(context, 0, contact);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Contact contact = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_view, parent, false);
        }

        TextView contactName = convertView.findViewById(R.id.contactListName);
        TextView contactPhone = convertView.findViewById(R.id.contactListPhone);

        contactName.setText(Objects.requireNonNull(contact).getName());
        contactPhone.setText(Objects.requireNonNull(contact).getPhone());

        return convertView;
    }
}
