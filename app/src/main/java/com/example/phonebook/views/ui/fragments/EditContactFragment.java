package com.example.phonebook.views.ui.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.FragmentEditContactBinding;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.service.Flags;
import com.example.phonebook.views.ui.activities.MainActivity;

import java.util.Objects;

public class EditContactFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentEditContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_edit_contact, container, false);
        binding.setContact((Contact)getActivity().getIntent().getSerializableExtra("contact"));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}