package com.example.phonebook.views.ui.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import com.example.phonebook.R;
import com.example.phonebook.databinding.FragmentConfirmDeletionBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

import java.util.Objects;

public class ConfirmDeletionFragment extends DialogFragment {

    public static final String TAG = "com.example.phonebook.views.fragments.ConfirmDeletionFragment";

    private MainActivityViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentConfirmDeletionBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_deletion, container, false);
        Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        binding.buttonCancelDeletion.setOnClickListener(v -> getDialog().dismiss());
        binding.buttonConfirmDeletion.setOnClickListener(v -> {
           mViewModel.deleteContact();
           NavDirections action = ConfirmDeletionFragmentDirections.actionDeleteBack();
           NavHostFragment.findNavController(this).navigate(action);
        });
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
    }
}