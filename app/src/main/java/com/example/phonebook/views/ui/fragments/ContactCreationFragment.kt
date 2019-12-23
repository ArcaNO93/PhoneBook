package com.example.phonebook.views.ui.fragments

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
import com.example.phonebook.databinding.ContactCreationFragmentBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.MainActivityViewModel

import javax.inject.Inject

class ContactCreationFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactCreationFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_creation_fragment, container, false)
        binding.contact = mViewModel.mContact
        binding.serviceCreate = mViewModel

        binding.contactCreateButton.setOnClickListener {
            if(mViewModel.addContact()) {
                val tmp: View? = requireActivity().currentFocus

                if(tmp != null) {
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
        ComponentProvider.addMainActViewModelsComponent()?.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}
