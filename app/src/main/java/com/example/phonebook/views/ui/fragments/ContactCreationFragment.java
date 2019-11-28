package com.example.phonebook.views.ui.fragments;

import androidx.annotation.NonNull;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ContactCreationFragmentBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

public class ContactCreationFragment extends Fragment {

    public static final String TAG = "com.example.phonebook.views.fragments.ContactCreationFragment";
    private MainActivityViewModel mViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ContactCreationFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.contact_creation_fragment, container, false);
        binding.setContact(mViewModel.getContact());
        binding.setServiceCreate(mViewModel);

        binding.contactCreateButton.setOnClickListener(contact -> {
            if(mViewModel.addContact()) {
                NavDirections action = ContactCreationFragmentDirections.actionCreateBack();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
