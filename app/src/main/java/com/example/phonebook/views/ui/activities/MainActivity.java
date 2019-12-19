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
import com.example.phonebook.utils.ComponentProvider;
import com.example.phonebook.utils.ViewModelFactory;
import com.example.phonebook.viewModels.MainActivityViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelFactory mViewModelFactory;
    private MainActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentProvider.getInstance().addMainActViewModelsComponent().inject(this);
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel.class);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        setSupportActionBar(binding.mainActivityToolbar);
    }

    @Override
    public void onStart() {
        super.onStart();
        mViewModel.init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        menu.findItem(R.id.menuActionEdit).setVisible(false);
        menu.findItem(R.id.menuActionDelete).setVisible(false);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuActionSignOut) {
            mViewModel.signOut();
            startActivity(new Intent(this, AuthorisationActivity.class));
            finish();
        }
        return false;
    }

    @Override
    public void onStop() {
        super.onStop();
        mViewModel.save();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ComponentProvider.getInstance().removeMainActViewModelsComponent();
    }
}