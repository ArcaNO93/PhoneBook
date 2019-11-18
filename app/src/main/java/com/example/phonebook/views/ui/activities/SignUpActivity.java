package com.example.phonebook.views.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivitySignUpBinding;
import com.example.phonebook.viewModels.SignUpActivityViewModel;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignUpActivityViewModel signUp = ViewModelProviders.of(this).get(SignUpActivityViewModel.class);
        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        binding.setUser(signUp);
        binding.setLifecycleOwner(this);

        signUp.getIsFinished().observe(this, (isFinish) -> {
            if(isFinish != null && isFinish)
                finish();
        });
    }

}
