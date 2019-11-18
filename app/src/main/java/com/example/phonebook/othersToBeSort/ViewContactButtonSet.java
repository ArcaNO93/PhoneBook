package com.example.phonebook.othersToBeSort;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.phonebook.R;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.views.ui.activities.ContactActivity;
import com.example.phonebook.views.ui.fragments.EditContactFragment;

import java.util.Objects;


public class ViewContactButtonSet extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_contact_button_set, container, false);

        ImageButton mReturnButton = view.findViewById(R.id.returnButton);
        ImageButton mEditContactButton = view.findViewById(R.id.editContactButton);
        ImageButton mDeleteContactButton = view.findViewById(R.id.deleteContactButton);

        Contact contact = (Contact) Objects.requireNonNull(getActivity()).getIntent().getSerializableExtra("ITEM");

        mReturnButton.setOnClickListener( v-> {
                getActivity().getIntent().putExtra("ITEM", contact);
                getActivity().getIntent().putExtra("ITEM_POSITION", getActivity().getIntent().getIntExtra("ITEM_POSITION",0));
                getActivity().setResult(0, getActivity().getIntent());
                getActivity().finish();
        });

        mEditContactButton.setOnClickListener( v -> {
            Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setTitle("Edit contact");
            FragmentTransaction fTrans = getActivity().getSupportFragmentManager().beginTransaction();
            fTrans.replace(R.id.ContainerForFragments, new EditContactFragment(), null);
            fTrans.replace(R.id.ContainerForButtons, new FinishEditContactButton());
            fTrans.commit();
        });

        mDeleteContactButton.setOnClickListener( v-> {
            ConfirmDeletion dialog = new ConfirmDeletion();
            dialog.show(getActivity().getSupportFragmentManager(), "deletionDialog");
        });

        return view;
    }
}