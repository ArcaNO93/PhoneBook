package com.example.phonebook.views.ui.fragments;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.LogInFragmentBinding;
import com.example.phonebook.viewModels.LogInViewModel;
import com.example.phonebook.views.ui.activities.MainActivity;

import static com.example.phonebook.model.service.Flags.TO_MAIN;

public class LogInFragment extends Fragment {

    public static final String TAG = "com.example.phonebook.views.fragments.LogInFragment";
    private LogInViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LogInViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogInFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.log_in_fragment, container, false);
        binding.setUser(mViewModel.getUser());
        binding.setService(mViewModel);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel.getIsLogged().observe(this, v -> startActivityForResult(new Intent(getActivity(), MainActivity.class), TO_MAIN));

        mViewModel.getToRegister().observe(this, toRegister -> {
            if(toRegister != null && toRegister) {
                NavDirections action = LogInFragmentDirections.actionLogInFragmentToRegisterFragment();
                NavHostFragment.findNavController(this).navigate(action);
            }
        });
    }

}
