package com.example.phonebook.ui.fragments

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
import com.example.phonebook.databinding.ConfirmDeletionBinding
import com.example.phonebook.ui.viewModels.ContactListViewModel
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class ContactDeletionFragment : DialogFragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ContactListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ConfirmDeletionBinding = DataBindingUtil.inflate(inflater, R.layout.confirm_deletion, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.contact = mViewModel.mContact
        binding.buttonCancelDeletion.setOnClickListener { dialog?.dismiss() }

        binding.buttonConfirmDeletion.setOnClickListener {
           mViewModel.deleteContact()
           NavHostFragment.findNavController(this).navigate(ContactDeletionFragmentDirections.actionDeleteBack())
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ContactListViewModel::class.java)
    }
}