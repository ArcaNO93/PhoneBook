package com.example.phonebook.views.ui.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.RegisterFragmentBinding;
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.utils.ViewModelFactory;
import com.example.phonebook.viewModels.RegisterViewModel;

import javax.inject.Inject;

public class RegisterFragment extends Fragment {

    private static final String TAG = "com.example.phonebook.views.fragments.RegisterFragment";

    @Inject
    ViewModelFactory mViewModelFactory;
    private RegisterViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentProvider.getInstance().addAuthActViewModelsComponent().inject(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RegisterViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RegisterFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false);
        binding.setUser(mViewModel.getUser());
        binding.setService(mViewModel);

        mViewModel.getRegistrationDone().observe(this, registrationDone -> {
            if(registrationDone != null && registrationDone) {
                NavHostFragment.findNavController(this).navigateUp();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.clean();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentProvider.getInstance().removeAuthActViewModelsComponent();
    }
}
