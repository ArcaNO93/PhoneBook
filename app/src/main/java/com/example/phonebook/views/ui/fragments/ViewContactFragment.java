package com.example.phonebook.views.ui.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.phonebook.R;
import com.example.phonebook.databinding.ViewContactFragmentBinding;
import com.example.phonebook.viewModels.MainActivityViewModel;

public class ViewContactFragment extends Fragment {

    public static final String TAG = "com.example.phonebook.views.fragments.ViewContactFragment";
    private MainActivityViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewContactFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.view_contact_fragment, container, false);
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
        menu.findItem(R.id.menuActionEdit).setVisible(true);
        menu.findItem(R.id.menuActionDelete).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        NavController navHostFragment = NavHostFragment.findNavController(this);
        switch(item.getItemId()) {
            case R.id.menuActionEdit:
                navHostFragment.navigate(ViewContactFragmentDirections.actionEdit());
                break;
            case R.id.menuActionDelete:
                navHostFragment.navigate(ViewContactFragmentDirections.actionDelete());
        }

        return super.onOptionsItemSelected(item);
    }
}
