package com.example.phonebook.model.repos

import com.example.phonebook.model.data.User
import com.example.phonebook.model.db.UsersDB
import com.example.phonebook.utils.ComponentProvider

import java.util.concurrent.Callable
import java.util.concurrent.Executors

import javax.inject.Inject

class UsersRepoByRoom @Inject constructor() : UsersRepo {

    init {
        ComponentProvider.getAppComponent()?.inject(this)
    }

    @Inject
    lateinit var usersDB: UsersDB

    override fun addUser(user: User): Long {
        val addUser: Callable<Long> = Callable {
            usersDB.getUsersDB().insertUser(user)
        }

        return Executors.newSingleThreadExecutor().submit(addUser).get()
    }

    override fun deleteUser(user: User) {
        //TODO right now not needed
    }

    override fun updateUser(ID: String, newUser: User) {
        //TODO right now not needed
    }

    override fun getUser(login: String): User? {
        val getUser: Callable<User?> = Callable {
            usersDB.getUsersDB().getUser(login)
        }

        return Executors.newSingleThreadExecutor().submit(getUser).get()
    }

}
