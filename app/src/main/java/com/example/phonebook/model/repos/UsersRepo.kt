package com.example.phonebook.model.repos

import com.example.phonebook.model.data.User

interface UsersRepo {
    fun addUser(user: User): Long
    fun deleteUser(user: User)
    fun updateUser(ID: String, newUser: User)
    fun getUser(login: String): User?
}
