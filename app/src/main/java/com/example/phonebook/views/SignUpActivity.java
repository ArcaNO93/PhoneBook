package com.example.phonebook.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivitySignUpBinding;
import com.example.phonebook.viewModels.SignUpViewModel;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignUpViewModel signUp = ViewModelProviders.of(this).get(SignUpViewModel.class);
        ActivitySignUpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        binding.setUser(signUp);
        binding.setLifecycleOwner(this);

        signUp.getIsFinished().observe(this, (isFinish) -> {
            if(isFinish != null && isFinish)
                finish();
        });
    }
}
