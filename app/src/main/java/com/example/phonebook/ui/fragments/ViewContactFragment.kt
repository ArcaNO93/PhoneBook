package com.example.phonebook.ui.fragments

import android.os.Bundle

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

import androidx.navigation.fragment.NavHostFragment

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup

import com.example.phonebook.R
import com.example.phonebook.databinding.ViewContactBinding
import com.example.phonebook.ui.viewModels.ContactListViewModel
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory

import javax.inject.Inject

class ViewContactFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ContactListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ViewContactBinding = DataBindingUtil.inflate(inflater, R.layout.view_contact, container, false)

        binding.contact = mViewModel.mContact
        requireActivity().invalidateOptionsMenu()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent()!!.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ContactListViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.menuActionSignOut).isVisible = false
        menu.findItem(R.id.menuActionEdit).isVisible = true
        menu.findItem(R.id.menuActionDelete).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navHostFragment = NavHostFragment.findNavController(this)

        when(item.itemId) {
            R.id.menuActionEdit -> navHostFragment.navigate(ViewContactFragmentDirections.actionEdit())
            R.id.menuActionDelete -> navHostFragment.navigate(ViewContactFragmentDirections.actionDelete())
        }

        return super.onOptionsItemSelected(item)
    }
}
