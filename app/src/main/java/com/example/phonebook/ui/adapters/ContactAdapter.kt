package com.example.phonebook.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.phonebook.databinding.ContactViewBinding
import com.example.phonebook.data.pojo.ContactView
import com.example.phonebook.utils.ContactClickCallback

import kotlin.collections.ArrayList

class ContactAdapter(callBack: ContactClickCallback) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val mContactClickCallback: ContactClickCallback = callBack

    private var mContacts: ArrayList<ContactView>? = null

    fun updateContactList(contacts: ArrayList<ContactView> ) {
        if(mContacts == null) {
            mContacts = contacts
            notifyDataSetChanged()
        } else {
            val result: DiffUtil.DiffResult  = DiffUtil.calculateDiff(object: DiffUtil.Callback() {

                override fun getOldListSize() = mContacts!!.size

                override fun getNewListSize() = contacts.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int)  = mContacts!![oldItemPosition] == contacts[newItemPosition]

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = mContacts!![oldItemPosition] == contacts[newItemPosition]
            })

            result.dispatchUpdatesTo(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding: ContactViewBinding = ContactViewBinding.inflate(LayoutInflater.from(parent.context), parent, false).apply {
            callback = mContactClickCallback
        }

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.contact = mContacts?.get(position)
        holder.binding.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return mContacts?.size ?: 0
    }

    inner class ContactViewHolder(val binding: ContactViewBinding) : RecyclerView.ViewHolder(binding.root)
}
