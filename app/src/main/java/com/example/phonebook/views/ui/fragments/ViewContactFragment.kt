package com.example.phonebook.views.ui.fragments

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
import com.example.phonebook.databinding.ViewContactFragmentBinding
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import com.example.phonebook.viewModels.MainActivityViewModel

import javax.inject.Inject

class ViewContactFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel:  MainActivityViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ViewContactFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.view_contact_fragment, container, false)

        binding.contact = mViewModel.mContact
        requireActivity().invalidateOptionsMenu()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.addMainActViewModelsComponent()?.inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MainActivityViewModel::class.java)
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
