package com.example.phonebook.views.ui.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.databinding.DataBindingUtil

import androidx.fragment.app.DialogFragment

import androidx.lifecycle.ViewModelProviders

import androidx.navigation.fragment.NavHostFragment

import com.example.phonebook.R
import com.example.phonebook.databinding.ConfirmDeletionFragmentBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.MainActivityViewModel


import javax.inject.Inject

class ConfirmDeletionFragment : DialogFragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ConfirmDeletionFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.confirm_deletion_fragment, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.buttonCancelDeletion.setOnClickListener { dialog?.dismiss() }

        binding.buttonConfirmDeletion.setOnClickListener {
           mViewModel.deleteContact()
           NavHostFragment.findNavController(this).navigate(ConfirmDeletionFragmentDirections.actionDeleteBack())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.addMainActViewModelsComponent()?.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel::class.java)
    }
}