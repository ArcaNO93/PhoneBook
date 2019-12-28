package com.example.phonebook.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.example.phonebook.R
import com.example.phonebook.databinding.ActivityMainBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.ui.viewModels.MainActivityViewModel
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel::class.java)
        val binding: ActivityMainBinding  = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        setSupportActionBar(binding.mainActivityToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.menuActionEdit).isVisible = false
        menu.findItem(R.id.menuActionDelete).isVisible = false
        super.onCreateOptionsMenu(menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menuActionSignOut) {
            startActivity(Intent(this, AuthorisationActivity::class.java))
            mViewModel.logOut()
            finish()
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.save()
        ComponentProvider.getInstance().removeMainComponent()
    }
}