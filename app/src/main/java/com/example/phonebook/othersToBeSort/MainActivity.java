package com.example.phonebook.othersToBeSort;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.example.phonebook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    static final int CONTACT_CREATION = 0;
    static final int CONTACT_EDIT = 1;
    static final int RESULT_CREATE = 2;
    static final int RESULT_DELETE = 3;
    static final int RESULT_EDIT = 4;
    static final String CONTACT_CREATION_FLAG = "com.example.phonebook.CONTACT_CREATION_FLAG";
    static final String VIEW_CONTACT_FLAG = "com.example.phonebook.VIEW_CONTACT_FLAG";

    private ArrayList<Contact> mContacts = new ArrayList<>();
    private ContactAdapter contactAddAdapter;
    private SharedPreferences mUsers;
    private SharedPreferences mUserContactList;
    private String currentUser = "";
    private DialogFragment progressBar = new ProgressBar();

    @SuppressWarnings("unchecked")
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainActivityToolbar = findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(mainActivityToolbar);

        FloatingActionButton mCreateNewContactButton = findViewById(R.id.newContactButton);
        ListView mPhoneBook = findViewById(R.id.phoneBook);
        contactAddAdapter = new ContactAdapter(this, mContacts);
        mUsers = getSharedPreferences("Users", Context.MODE_PRIVATE);
        currentUser = mUsers.getString("currentUser", "");
        mPhoneBook.setAdapter(contactAddAdapter);
        mUserContactList = getSharedPreferences("usersContactLists", Context.MODE_PRIVATE);
        progressBar.show(getSupportFragmentManager(), "progressBar");

        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( f -> {
                    progressBar.dismiss();
                    try {
                        mContacts.addAll((ArrayList<Contact>) ObjectSerializer
                                .deserialize(mUserContactList
                                        .getString(currentUser, ObjectSerializer.serialize(new ArrayList<>()))));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    contactAddAdapter.notifyDataSetChanged();
                });

        mCreateNewContactButton.setOnClickListener(v -> {
            Intent createNewContactIntent = new Intent(MainActivity.this, ContactActivity.class);
            createNewContactIntent.putExtra("FLAG", CONTACT_CREATION_FLAG);
            startActivityForResult(createNewContactIntent, CONTACT_CREATION);
        });

        mPhoneBook.setOnItemClickListener((parent, view, position, id) -> {
            Intent viewContactIntent = new Intent(MainActivity.this, ContactActivity.class);
            viewContactIntent.putExtra("ITEM", mContacts.get(position));
            viewContactIntent.putExtra("ITEM_POSITION", position);
            viewContactIntent.putExtra("FLAG", VIEW_CONTACT_FLAG);
            startActivityForResult(viewContactIntent, CONTACT_EDIT);
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
                SharedPreferences.Editor mUsersEditor = mUsers.edit();
                mUsersEditor.putBoolean("Signed up", false);
                mUsersEditor.apply();
                setResult(RESULT_FIRST_USER, getIntent());
                finish();
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CONTACT_CREATION) {
            if (resultCode == RESULT_CREATE) {
                Contact newContact = (Contact) data.getSerializableExtra("ITEM");
                mContacts.add(newContact);
                contactAddAdapter.notifyDataSetChanged();
            }
        }
        if (requestCode == CONTACT_EDIT) {
            if (resultCode == RESULT_DELETE) {
                mContacts.remove(mContacts.get(data.getIntExtra("ITEM_POSITION", 0)));
                contactAddAdapter.notifyDataSetChanged();
            }
            if (resultCode == RESULT_EDIT) {
                mContacts.set(data.getIntExtra("ITEM_POSITION", 0), (Contact) data.getSerializableExtra("ITEM"));
                contactAddAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setResult(RESULT_CANCELED, getIntent());
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences.Editor mUserContactListEditor = mUserContactList.edit();
        try {
            mUserContactListEditor.putString(currentUser, ObjectSerializer.serialize(mContacts));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mUserContactListEditor.apply();
    }
}