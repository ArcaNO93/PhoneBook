package com.example.phonebook.views.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivitySignInBinding;
import com.example.phonebook.viewModels.SignInActivityViewModel;

public class SignInActivity extends AppCompatActivity {

    private static final int TO_MAIN = 2;
    private SignInActivityViewModel signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signIn = ViewModelProviders.of(this).get(SignInActivityViewModel.class);
        ActivitySignInBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_in);
        binding.setUser(signIn);
        binding.setLifecycleOwner(this);
        signIn.init();
        signIn.getIsLogged().observe(this, (isLogged) -> {
            if (isLogged != null && isLogged) {
                startActivityForResult(new Intent(SignInActivity.this, MainActivity.class), TO_MAIN);
            }
        });

        signIn.getToSignUp().observe(this, (toSignUp) -> {
            if (toSignUp != null && toSignUp) {
                startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        signIn.clean();
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

