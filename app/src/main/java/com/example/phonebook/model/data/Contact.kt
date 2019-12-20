package com.example.phonebook.model.data

import com.example.phonebook.dagger.scopes.ActivitiesScope

import java.util.UUID

import javax.inject.Inject

@ActivitiesScope
data class Contact (val ID: String) {

    lateinit var contactName: String
    lateinit var contactPhone: String
    lateinit var contactEmail: String
    lateinit var contactAddress: String

    @Inject constructor(): this(UUID.randomUUID().toString()) {
        contactName = ""
        contactPhone = ""
        contactEmail = ""
        contactAddress = ""
    }

    fun clean() {
        contactName = ""
        contactPhone = ""
        contactEmail = ""
        contactAddress = ""
    }
}
