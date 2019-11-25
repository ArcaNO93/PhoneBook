package com.example.phonebook.views.ui.fragments;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.RegisterFragmentBinding;
import com.example.phonebook.viewModels.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private static final String TAG = "com.example.phonebook.views.fragments.RegisterFragment";
    private RegisterViewModel mViewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RegisterFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        binding.setUser(mViewModel.getUser());
        binding.setService(mViewModel);

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(),
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Navigation.findNavController(binding.getRoot()).navigateUp(); // TODO: not working, fix it
                    }
                });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel.getRegistrationDone().observe(this, registrationDone -> {
            if(registrationDone != null && registrationDone) {
                NavDirections action = RegisterFragmentDirections.actionRegisterFragmentToLogInFragment();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });



    }
}
