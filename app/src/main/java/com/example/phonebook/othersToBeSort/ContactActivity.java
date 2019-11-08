package com.example.phonebook.othersToBeSort;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.example.phonebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactActivity extends AppCompatActivity {
    public static final String CONTACT_CREATION_FRAGMENT = "com.example.phonebook.CONTACT_CREATION_FRAGMENT";
    public static final String CONTACT_VIEW_FRAGMENT = "com.example.phonebook.CONTACT_VIEW_FRAGMENT";
    public static final String CONTACT_EDIT_FRAGMENT = "com.example.phonebook.CONTACT_EDIT_FRAGMENT";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Toolbar contactActivityToolbar = findViewById(R.id.contactActivityToolbar);
        FloatingActionButton mOkButton = findViewById(R.id.createContactButton);
        FragmentTransaction fTrans = getSupportFragmentManager().beginTransaction();
        setSupportActionBar(contactActivityToolbar);
        DialogFragment progressBar = new ProgressBar();

        if (Objects.requireNonNull(getIntent().getStringExtra("FLAG")).equals(MainActivity.CONTACT_CREATION_FLAG)) {
            Objects.requireNonNull(getSupportActionBar()).setTitle("Create new contact");
            fTrans.add(R.id.ContainerForFragments, new CreateContact(), CONTACT_CREATION_FRAGMENT);
            mOkButton.show();
            fTrans.commit();
        }

        if (Objects.equals(getIntent().getStringExtra("FLAG"), MainActivity.VIEW_CONTACT_FLAG)) {
            Objects.requireNonNull(getSupportActionBar()).setTitle(" View contact");
            mOkButton.hide();
            progressBar.show(getSupportFragmentManager(), "progressBar");
            Observable.timer(2000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe( f -> {
                        progressBar.dismiss();
                        fTrans.add(R.id.ContainerForFragments, new ViewContact(), CONTACT_VIEW_FRAGMENT);
                        fTrans.add(R.id.ContainerForButtons, new ViewContactButtonSet());
                        fTrans.commit();
                    });
        }

        mOkButton.setOnClickListener(v-> {
            CreateContact fragment = (CreateContact) getSupportFragmentManager().findFragmentByTag(ContactActivity.CONTACT_CREATION_FRAGMENT);
            Contact contact = new Contact();
            assert fragment != null;

            if(fragment.getContactName().isEmpty()) {
                Toast mErrorNoName = Toast.makeText(getApplicationContext(), getString(R.string.error_no_name), Toast.LENGTH_SHORT);
                mErrorNoName.show();
            }
            else {
                contact.setName(fragment.getContactName());
                contact.setPhone(fragment.getContactPhone());
                contact.setEmail(fragment.getContactEmail());
                contact.setAddress(fragment.getContactAddress());
                getIntent().putExtra("ITEM", contact);
                setResult(MainActivity.RESULT_CREATE, getIntent());
                finish();
            }
        });
    }
}
