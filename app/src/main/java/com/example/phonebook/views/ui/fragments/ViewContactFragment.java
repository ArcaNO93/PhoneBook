package com.example.phonebook.views.ui.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.FragmentViewContactBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

public class ViewContactFragment extends Fragment {

    public static final String TAG = "com.example.phonebook.views.fragments.ViewContactFragment";
    private MainActivityViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentViewContactBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_contact, container, false);
        binding.setContact(mViewModel.getContact());
        requireActivity().invalidateOptionsMenu();
        return binding.getRoot();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel.class);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.findItem(R.id.menuActionSignOut).setVisible(false);
        menu.findItem(R.id.menuActionChange).setVisible(true);
        menu.findItem(R.id.confirmDeletion).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavigationUI.onNavDestinationSelected(item, NavHostFragment.findNavController(this));
        return super.onOptionsItemSelected(item);
    }
}