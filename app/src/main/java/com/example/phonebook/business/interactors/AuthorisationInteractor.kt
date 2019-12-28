package com.example.phonebook.business.interactors

interface AuthorisationInteractor {
    fun register(): Boolean
    fun validateData(): Boolean
    fun init(): Boolean
    fun userClean()
}