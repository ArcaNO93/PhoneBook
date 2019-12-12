package com.example.phonebook.views.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.utils.ViewModelFactory;
import com.example.phonebook.viewModels.LogInViewModel;

import javax.inject.Inject;

public class AuthorisationActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory viewModelFactory;
    private LogInViewModel logInViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentProvider.getInstance().addAuthActViewModelsComponent().inject(this);

        logInViewModel = ViewModelProviders.of(this, viewModelFactory).get(LogInViewModel.class);

        if(logInViewModel.init()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        setContentView(R.layout.activity_authorisation);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentProvider.getInstance().removeAuthActViewModelsComponent();
    }
}

