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

import com.example.phonebook.BR;
import com.example.phonebook.R;
import com.example.phonebook.databinding.FragmentContactCreationBinding;
import com.example.phonebook.viewModels.CreateContactFragmentViewModel;
import com.example.phonebook.views.ui.activities.MainActivity;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class CreateContactFragment extends Fragment {

    private CreateContactFragmentViewModel createContactFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentContactCreationBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_creation, container, false);
        binding.setContact(createContactFragment.getContact());
        binding.setService(createContactFragment);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        createContactFragment.getLiveContact().observe(this, contact -> {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            intent.putExtra("newContact", contact);
            Objects.requireNonNull(getActivity()).setResult(RESULT_OK, intent);
            getActivity().finish();
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createContactFragment = ViewModelProviders.of(this).get(CreateContactFragmentViewModel.class);
    }
}
