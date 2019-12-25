package com.example.phonebook.views.ui.fragments

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
import com.example.phonebook.databinding.LogInFragmentBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.LogInViewModel
import com.example.phonebook.views.ui.activities.MainActivity

import javax.inject.Inject

class LogInFragment : Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mViewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addAuthActViewModelsComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, viewModelFactory).get(LogInViewModel::class.java)

        mViewModel.isLogged.observe(this, Observer { isLogged ->
            if(isLogged != null && isLogged) {
                startActivity(Intent(activity, MainActivity::class.java))
            }
        })
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: LogInFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.log_in_fragment, container, false)
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
        ComponentProvider.getInstance().removeAuthActViewModelsComponent()
    }
}
