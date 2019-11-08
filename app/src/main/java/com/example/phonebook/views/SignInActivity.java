package com.example.phonebook.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivitySignInBinding;
import com.example.phonebook.othersToBeSort.MainActivity;
import com.example.phonebook.viewModels.SignInViewModel;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SignInViewModel signIn = ViewModelProviders.of(this).get(SignInViewModel.class);
        ActivitySignInBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        binding.setUser(signIn);
        binding.setLifecycleOwner(this);
        signIn.init();

        signIn.getIsLogged().observe(this, (isLogged) -> {
            if (isLogged != null && isLogged) {
                startActivity(new Intent(SignInActivity.this, MainActivity.class));
            }
        });

        signIn.getToSignUp().observe(this, (toSignUp) -> {
            if (toSignUp != null && toSignUp) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }
}
