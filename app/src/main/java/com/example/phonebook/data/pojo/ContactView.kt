package com.example.phonebook.data.pojo

import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.Contact
import java.util.*
import javax.inject.Inject

@ActivitiesScope
data class ContactView(
        val contactViewID: String,
        var contactViewName: String,
        var contactViewPhone: String,
        var contactViewEmail: String,
        var contactViewAddress: String) {

    @Inject constructor(): this(
            UUID.randomUUID().toString(),
            "",
            "",
            "",
            "")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactView

        if (contactViewID != other.contactViewID) return false

        return true
    }

    override fun hashCode(): Int {
        return contactViewID.hashCode()
    }
}