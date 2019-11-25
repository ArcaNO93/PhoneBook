package com.example.phonebook.views.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.viewModels.AuthorisationActivityViewModel;

import static com.example.phonebook.model.service.Flags.TO_MAIN;

public class AuthorisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AuthorisationActivityViewModel viewModel = ViewModelProviders.of(this).get(AuthorisationActivityViewModel.class);
        if(viewModel.init())
            startActivityForResult(new Intent(this, MainActivity.class), TO_MAIN);
        setContentView(R.layout.activity_authorisation);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == TO_MAIN) {
            if(resultCode == RESULT_CANCELED) {
                finish();
            }
        }
    }
}

