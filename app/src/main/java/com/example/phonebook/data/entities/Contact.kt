package com.example.phonebook.data.entities

import com.example.phonebook.dagger.scopes.ActivitiesScope

import javax.inject.Inject

@ActivitiesScope
data class Contact
@Inject constructor(
        val contactID: String,
        var contactName: String,
        var contactPhone: String,
        var contactEmail: String,
        var contactAddress: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Contact

        if (contactID != other.contactID) return false

        return true
    }

    override fun hashCode(): Int {
        return contactID.hashCode()
    }
}
