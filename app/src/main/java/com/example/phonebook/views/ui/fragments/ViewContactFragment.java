package com.example.phonebook.views.ui.fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.FragmentViewContactBinding;
import com.example.phonebook.model.data.Contact;

public class ViewContactFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentViewContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_contact, container, false);
        binding.setContact((Contact)getActivity().getIntent().getSerializableExtra("contact"));
        return binding.getRoot();
    }
}
