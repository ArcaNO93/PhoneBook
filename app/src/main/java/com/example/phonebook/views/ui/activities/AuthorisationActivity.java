package com.example.phonebook.views.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.viewModels.AuthorisationActivityViewModel;

public class AuthorisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthorisationActivityViewModel viewModel = ViewModelProviders.of(this).get(AuthorisationActivityViewModel.class);

        if(viewModel.init()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_authorisation);
    }
}

