package com.example.phonebook.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.phonebook.databinding.ContactViewBinding;
import com.example.phonebook.model.data.Contact;
import com.example.phonebook.model.service.ContactClickCallback;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder>{

    @Nullable
    private final ContactClickCallback mContactClickCallback;

    private ArrayList<Contact> mContacts;

    public ContactAdapter (@Nullable ContactClickCallback _callBack) {
        mContactClickCallback = _callBack;
    }

    public void updateContactList(ArrayList<Contact> _contacts) {
        if(mContacts == null) {
            mContacts = _contacts;
            notifyDataSetChanged();
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

                @Override
                public int getOldListSize() {
                    return mContacts.size();
                }

                @Override
                public int getNewListSize() {
                    return _contacts.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mContacts.get(oldItemPosition).getID().equals(_contacts.get(newItemPosition).getID());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return mContacts.get(oldItemPosition).equals(_contacts.get(newItemPosition));
                }
            });

            mContacts = _contacts;
            result.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactViewBinding binding = ContactViewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        binding.setCallback(mContactClickCallback);
        return new ContactViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.binding.setContact(mContacts.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mContacts == null ? 0 : mContacts.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder{

        private final ContactViewBinding binding;

        ContactViewHolder (ContactViewBinding _binding) {
            super(_binding.getRoot());
            this.binding = _binding;
        }
    }
}
