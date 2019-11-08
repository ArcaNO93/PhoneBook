package com.example.phonebook;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private SharedPreferences mUsers;
    private EditText mNewLogin;
    private EditText mNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mNewLogin = findViewById(R.id.createUserFormLoginEnterBox);
        mNewPassword = findViewById(R.id.createUserFormPasswordEnterBox);

        Button mSignUp = findViewById(R.id.signUpButton);

        mUsers = getSharedPreferences("Users", Context.MODE_PRIVATE);
        mSignUp.setOnClickListener(v -> {
            SharedPreferences.Editor addUser = mUsers.edit();
            if (mNewLogin.getText().toString().length() == 0) {
                Toast mErrorNoLogin = Toast.makeText(v.getContext(), getString(R.string.error_no_login), Toast.LENGTH_SHORT);
                mErrorNoLogin.show();
            } else if (mNewPassword.getText().toString().length() == 0) {
                Toast mErrorNoPassword = Toast.makeText(v.getContext(), getString(R.string.error_no_password), Toast.LENGTH_SHORT);
                mErrorNoPassword.show();
            } else if (!mUsers.contains(mNewLogin.getText().toString())) {
                addUser.putString(mNewLogin.getText().toString(), mNewPassword.getText().toString());
                addUser.apply();
                Toast mUserCreationSuccess = Toast.makeText(v.getContext(), getString(R.string.user_creation_success), Toast.LENGTH_SHORT);
                mUserCreationSuccess.show();
                setResult(RESULT_OK, getIntent());
                finish();
            } else {
                Toast mErrorUserExists = Toast.makeText(v.getContext(), getString(R.string.error_login_exists), Toast.LENGTH_SHORT);
                mErrorUserExists.show();
            }
        });
    }
}
