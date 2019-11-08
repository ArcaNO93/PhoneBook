package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.phonebook.R;

import java.util.Objects;

public class FinishEditContactButton extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finish_edit_contact_button, container, false);
        ImageButton mFinishEditContactButton = view.findViewById(R.id.FinishEditContactButton);

        mFinishEditContactButton.setOnClickListener(v-> {
            assert getFragmentManager() != null;
            EditContact fragment = (EditContact) getFragmentManager().findFragmentByTag(ContactActivity.CONTACT_EDIT_FRAGMENT);
            Contact contact = (Contact) Objects.requireNonNull(getActivity()).getIntent().getSerializableExtra("ITEM");
            assert fragment != null;
            if(fragment.getContactName().isEmpty()) {
                Toast mErrorNoName = Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), getString(R.string.error_no_name), Toast.LENGTH_SHORT);
                mErrorNoName.show();
            }
            else {
                assert contact != null;
                contact.setName(fragment.getContactName());
                contact.setPhone(fragment.getContactPhone());
                contact.setEmail(fragment.getContactEmail());
                contact.setAddress(fragment.getContactAddress());
                getActivity().getIntent().putExtra("ITEM_POSITION", getActivity().getIntent().getIntExtra("ITEM_POSITION",0));
                getActivity().getIntent().putExtra("ITEM", contact);
                getActivity().setResult(MainActivity.RESULT_EDIT, getActivity().getIntent());
                getActivity().finish();
            }
        });
        return view;
    }

}
