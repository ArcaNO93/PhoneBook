package com.example.phonebook.ui.fragments

import android.content.Context
import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager

import com.example.phonebook.R
import com.example.phonebook.data.pojo.ContactView
import com.example.phonebook.databinding.ContactCreationBinding
import com.example.phonebook.ui.viewModels.ContactCreationViewModel
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class ContactCreationFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ContactCreationViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactCreationBinding = DataBindingUtil.inflate(inflater, R.layout.contact_creation, container, false)
        val mContact = ContactView()
        binding.contact = mContact
        binding.serviceCreate = mViewModel

        binding.contactCreateButton.setOnClickListener {
            if(mViewModel.addContact(mContact)) {
                val tmp: View? = requireActivity().currentFocus

                if (tmp != null) {
                    val inputManager: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(tmp.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

                }
                NavHostFragment.findNavController(this).navigate(ContactCreationFragmentDirections.actionCreateBack())
            }
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ContactCreationViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}
