package com.example.phonebook.ui.fragments

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.phonebook.R
import com.example.phonebook.databinding.LogInBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.ui.viewModels.LogInViewModel
import com.example.phonebook.ui.activities.MainActivity
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class LogInFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addAuthComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(LogInViewModel::class.java)

        mViewModel.isLogged.observe(this, Observer { isLogged ->
            if(isLogged != null && isLogged) {
                startActivity(Intent(activity, MainActivity::class.java))
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: LogInBinding = DataBindingUtil.inflate(inflater, R.layout.log_in, container, false)
        binding.user = mViewModel.mUser
        binding.service = mViewModel

        binding.toRegisterButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(LogInFragmentDirections.actionRegister())
        }

        return binding.root
    }

    override fun onPause() {
        super.onPause()
        mViewModel.clean()
    }

    override fun onDestroy() {
        super.onDestroy()
        ComponentProvider.getInstance().removeAuthComponent()
    }
}
