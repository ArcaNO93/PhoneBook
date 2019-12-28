package com.example.phonebook.data.repos

interface ServiceRepo {
    fun setCurrentUser(currentUser: String)
    fun setSignedUp(flag: Boolean)
    fun getCurrentUser(): String?
    fun getSignedUp(): Boolean
}
