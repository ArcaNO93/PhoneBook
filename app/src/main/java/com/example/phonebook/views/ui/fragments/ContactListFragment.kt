package com.example.phonebook.views.ui.fragments

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle
import android.util.Log

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.phonebook.R
import com.example.phonebook.databinding.ContactListFragmentBinding
import com.example.phonebook.model.data.Contact
import com.example.phonebook.model.service.ContactClickCallback
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.MainActivityViewModel
import com.example.phonebook.views.adapters.ContactAdapter

import javax.inject.Inject

class ContactListFragment : Fragment() {

    private lateinit var mContactAdapter: ContactAdapter

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainActViewModelsComponent()!!.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel::class.java)

        mContactAdapter = ContactAdapter(object : ContactClickCallback {
            override fun onClick(contact: Contact) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    mViewModel.mContact = contact

                    NavHostFragment.findNavController(this@ContactListFragment).navigate(ContactListFragmentDirections.actionView())
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactListFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list_fragment, container, false)

        binding.service = mViewModel
        binding.phoneBook.adapter = mContactAdapter

        binding.newContactButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ContactListFragmentDirections.actionCreate())
            mViewModel.mContact = Contact()
        }

        mViewModel.mContacts.observe(this, Observer { contacts ->
            if (contacts != null) {
                mContactAdapter.updateContactList(contacts)
            }
        })

        return binding.root
    }

}
