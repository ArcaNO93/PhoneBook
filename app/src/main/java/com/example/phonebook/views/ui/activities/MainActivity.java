package com.example.phonebook.views.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import androidx.databinding.DataBindingUtil;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProviders;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivityMainBinding;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.service.ContactClickCallback;
import com.example.phonebook.viewModels.MainActivityViewModel;
import com.example.phonebook.views.adapters.ContactAdapter;


public class MainActivity extends AppCompatActivity {

    private static final int CONTACT_CREATION = 0;
    private static final int CONTACT_VIEW = 1;
    private MainActivityViewModel main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        main = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        ContactAdapter mContactAdapter = new ContactAdapter(mContactClickCallback);
        mainActivityBinding.phoneBook.setAdapter(mContactAdapter);

        mainActivityBinding.setLifecycleOwner(this);

        setSupportActionBar(mainActivityBinding.mainActivityToolbar);

        main.init();

        main.getCurrentContactList().observe(this, contacts -> {
            if(contacts != null) {
                mContactAdapter.updateContactList(contacts);
            }
        });

        mainActivityBinding.newContactButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, ContactActivity.class);
            intent.putExtra("fragment", "create");
            startActivityForResult(intent, 0);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menuActionSignOut:
                main.signOut();
                setResult(RESULT_FIRST_USER, new Intent(this, SignInActivity.class));
                finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CONTACT_CREATION) {
            if(resultCode == RESULT_OK) {
                main.addContact((Contact)data.getSerializableExtra("newContact"));
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        main.save();
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, new Intent(this, SignInActivity.class));
        finish();
    }

    private final ContactClickCallback mContactClickCallback = _contact -> {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
            Intent intent = new Intent(this, ContactActivity.class);
            intent.putExtra("fragment", "view");
            intent.putExtra("contact", _contact);
            startActivity(intent);
        }
    };
}