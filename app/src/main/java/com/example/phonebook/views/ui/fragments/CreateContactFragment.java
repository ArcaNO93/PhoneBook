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
import com.example.phonebook.databinding.FragmentContactCreationBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

public class CreateContactFragment extends Fragment {

    private MainActivityViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentContactCreationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_creation, container, false);
        binding.setContact(viewModel.getContact());
        binding.setServiceCreate(viewModel);

        binding.contactCreateButton.setOnClickListener(contact -> {
            if(viewModel.addContact()) {
                NavDirections action = CreateContactFragmentDirections.actionCreateBack();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }
}
