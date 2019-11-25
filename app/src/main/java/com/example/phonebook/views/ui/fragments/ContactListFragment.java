package com.example.phonebook.views.ui.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ContactListFragmentBinding;
import com.example.phonebook.model.service.ContactClickCallback;
import com.example.phonebook.viewModels.MainActivityViewModel;
import com.example.phonebook.views.adapters.ContactAdapter;

public class ContactListFragment extends Fragment {

    public static final String TAG = "com.example.phonebook.views.fragments.ContactListFragment";
    private ContactAdapter mContactAdapter;
    private MainActivityViewModel mViewModel;

    public static ContactListFragment newInstance() {
        return new ContactListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContactAdapter = new ContactAdapter(mContactClickCallback);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        mViewModel.init();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ContactListFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.contact_list_fragment, container, false);
        binding.setService(mViewModel);
        binding.phoneBook.setAdapter(mContactAdapter);
        binding.newContactButton.setOnClickListener(v -> NavHostFragment.findNavController(this).navigate(ContactListFragmentDirections.actionCreate()));
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.getCurrentContactList().observe(this, contacts -> {
            if(contacts != null) {
                mContactAdapter.updateContactList(contacts);
            }
        });
    }

    private final ContactClickCallback mContactClickCallback = _contact -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            mViewModel.setContact(_contact);
            NavHostFragment.findNavController(this).navigate(ContactListFragmentDirections.actionView());
        }
    };
}