package com.example.phonebook.views.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivityContactBinding;
import com.example.phonebook.othersToBeSort.ViewContactButtonSet;
import com.example.phonebook.viewModels.ContactActivityViewModel;
import com.example.phonebook.views.ui.fragments.CreateContactFragment;
import com.example.phonebook.views.ui.fragments.ViewContactFragment;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_contact);
        ContactActivityViewModel contactActivity = ViewModelProviders.of(this).get(ContactActivityViewModel.class);

        setSupportActionBar(binding.contactActivityToolbar);
        binding.setLifecycleOwner(this);
        contactActivity.init(Objects.requireNonNull(getIntent().getStringExtra("fragment")));

        contactActivity.getBuild().observe(this, build -> {
            FragmentManager fragmentManager = getSupportFragmentManager();
            if(build != null && build.equals("view")) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.ContainerForFragments, new ViewContactFragment())
                        .add(R.id.ContainerForButtons, new ViewContactButtonSet())
                        .commit();
            }
            if(build != null && build.equals("create")) {
                fragmentManager
                        .beginTransaction()
                        .add(R.id.ContainerForFragments, new CreateContactFragment())
                        .commit();
            }
        });
    }
}
