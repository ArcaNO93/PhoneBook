package com.example.phonebook.views.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ActivityMainBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.mainActivityToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.createContactFragment).setVisible(false);
        menu.findItem(R.id.confirmDeletionDialogFragment).setVisible(false);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem _item) {
        int id = _item.getItemId();
        switch (id) {
            case R.id.menuActionSignOut:
                mViewModel.signOut();
                startActivity(new Intent(this, AuthorisationActivity.class));
                finish();
        }
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        mViewModel.save();
    }
}