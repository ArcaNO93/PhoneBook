package com.example.phonebook.data.repos

import com.example.phonebook.data.entities.User

interface UsersRepo {
    fun addUser(user: User): Long
    fun deleteUser(user: User)
    fun updateUser(ID: String, newUser: User)
    fun getUser(login: String): User?
}
