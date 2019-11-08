package com.example.phonebook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class SignInActivity extends AppCompatActivity {

    static final int TO_MAIN = 1;
    private EditText mLogin;
    private EditText mPassword;
    private SharedPreferences mUsers;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_in);
        mLogin = findViewById(R.id.loginFormLoginEnterBox);
        mPassword = findViewById(R.id.loginFormPasswordEnterBox);
        mUsers = getSharedPreferences("Users", Context.MODE_PRIVATE);
        DialogFragment progressBar = new ProgressBar();

        if(mUsers.getBoolean("Signed up", false)) {
            Intent skipSignUp = new Intent(SignInActivity.this, MainActivity.class);
            startActivityForResult(skipSignUp, TO_MAIN);
        }

        SharedPreferences.Editor mUsersEditor = mUsers.edit();

        if(!mUsers.contains("Signed up")){
            mUsersEditor.putBoolean("Signed up", false);
            mUsersEditor.apply();
        }

        Button mSignInButton = findViewById(R.id.signInButton);
        Button mGoToSignUpButton = findViewById(R.id.goToSignUpButton);

        mSignInButton.setOnClickListener(v -> {
            if (mLogin.getText().toString().length() == 0) {
                Toast mErrorNoLogin = Toast.makeText(v.getContext(), getString(R.string.error_no_login), Toast.LENGTH_SHORT);
                mErrorNoLogin.show();
            } else if (mPassword.getText().toString().length() == 0) {
                    Toast mErrorNoPassword = Toast.makeText(v.getContext(), getString(R.string.error_no_password), Toast.LENGTH_SHORT);
                    mErrorNoPassword.show();
                } else if (!mUsers.contains(mLogin.getText().toString())) {
                    Toast errorNoUser = Toast.makeText(getApplicationContext(), getString(R.string.error_account_does_not_exists), Toast.LENGTH_SHORT);
                    errorNoUser.show();
            } else if(mUsers.getString(mLogin.getText().toString(), "").equals(mPassword.getText().toString())) {
                mUsersEditor.putBoolean("Signed up", true);
                mUsersEditor.putString("currentUser", mLogin.getText().toString());
                mUsersEditor.apply();
                progressBar.show(getSupportFragmentManager(), "progressBar");
                Observable.timer(2000, TimeUnit.MILLISECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe( f -> {
                            progressBar.dismiss();
                            Intent enterIntent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivityForResult(enterIntent, TO_MAIN);
                        });
            } else {
                Toast errorWrongPassword = Toast.makeText(getApplicationContext(), getString(R.string.error_wrong_password), Toast.LENGTH_SHORT);
                errorWrongPassword.show();
            }
        });

        mGoToSignUpButton.setOnClickListener(v-> {
            Intent goToSignUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(goToSignUpIntent);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TO_MAIN) {
            if (resultCode == RESULT_CANCELED) {
                finish();
                System.exit(0);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mLogin.setText("");
        mPassword.setText("");
    }

}
