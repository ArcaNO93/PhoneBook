package com.example.phonebook.views.ui.fragments

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.phonebook.R
import com.example.phonebook.databinding.RegisterFragmentBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.RegisterViewModel

import javax.inject.Inject

class RegisterFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addAuthActViewModelsComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, savedInstanceState:  Bundle?): View? {
        val binding: RegisterFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.register_fragment, container, false)
        binding.user = mViewModel.mUser
        binding.service = mViewModel

        mViewModel.mRegistrationDone.observe(this, Observer { registrationDone ->
            if(registrationDone != null && registrationDone) {
                NavHostFragment.findNavController(this).navigateUp()
            }
        })

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
