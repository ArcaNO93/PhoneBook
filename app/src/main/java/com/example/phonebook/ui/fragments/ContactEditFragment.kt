package com.example.phonebook.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import com.example.phonebook.R
import com.example.phonebook.databinding.ContactEditBinding
import com.example.phonebook.ui.viewModels.ContactListViewModel
import com.example.phonebook.utils.ComponentProvider
import com.example.phonebook.utils.ViewModelFactory
import javax.inject.Inject

class ContactEditFragment : Fragment() {

    @Inject lateinit var mViewModelFactory: ViewModelFactory
    private lateinit var mViewModel: ContactListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: ContactEditBinding = DataBindingUtil.inflate(inflater, R.layout.contact_edit, container, false)
        binding.contact = mViewModel.mContact

        binding.contactEditButton.setOnClickListener {
            if (mViewModel.updateContact()) {
                val tmp: View? = requireActivity().currentFocus

                if (tmp != null) {
                    val inputManager: InputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputManager.hideSoftInputFromWindow(tmp.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                }

                NavHostFragment.findNavController(this).navigate(ContactEditFragmentDirections.actionEditBack())
            }
        }
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentProvider.getInstance().addMainComponent().inject(this)
        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(ContactListViewModel::class.java)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}