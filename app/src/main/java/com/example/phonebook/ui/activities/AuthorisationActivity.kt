package com.example.phonebook.ui.activities

import android.content.Intent
import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import com.example.phonebook.R
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.ui.viewModels.AuthorisationActivityViewModel
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class AuthorisationActivity : AppCompatActivity() {

    @Inject lateinit var viewModelFactory : ViewModelFactory
    private lateinit var authorisationActivityViewModel: AuthorisationActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addAuthComponent().inject(this)

        authorisationActivityViewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthorisationActivityViewModel::class.java)
        if(authorisationActivityViewModel.init()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        setContentView(R.layout.activity_authorisation)
    }

    override fun onDestroy() {
        super.onDestroy()
        ComponentProvider.getInstance().removeAuthComponent()
    }
}

