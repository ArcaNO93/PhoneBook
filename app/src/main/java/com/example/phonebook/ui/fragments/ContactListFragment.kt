package com.example.phonebook.ui.fragments

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders

import android.os.Bundle

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.phonebook.R
import com.example.phonebook.data.pojo.ContactView
import com.example.phonebook.databinding.ContactListBinding
import com.example.phonebook.utils.ContactClickCallback
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.ui.adapters.ContactAdapter
import com.example.phonebook.ui.viewModels.ContactListViewModel
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class ContactListFragment : Fragment() {

    private lateinit var mContactAdapter: ContactAdapter

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ContactListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent()!!.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ContactListViewModel::class.java)

        mContactAdapter = ContactAdapter(object : ContactClickCallback {
            override fun onClick(contact: ContactView) {
                if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                    mViewModel.mContact = contact
                    NavHostFragment.findNavController(this@ContactListFragment).navigate(ContactListFragmentDirections.actionView())
                }
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactListBinding = DataBindingUtil.inflate(inflater, R.layout.contact_list, container, false)
        binding.phoneBook.adapter = mContactAdapter
        binding.newContactButton.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(ContactListFragmentDirections.actionCreate())
        }

        mViewModel.getContactList().observe(this, Observer { contacts ->
            mContactAdapter.updateContactList(contacts)
        })

        return binding.root
    }
}
