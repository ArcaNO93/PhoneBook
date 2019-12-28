package com.example.phonebook.data.repos

import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.User
import com.example.phonebook.data.room.db.UsersDB
import com.example.phonebook.utils.ComponentProvider

import java.util.concurrent.Callable
import java.util.concurrent.Executors

import javax.inject.Inject

@ActivitiesScope
class UsersRepoByRoom @Inject constructor(): UsersRepo {

    init {
        ComponentProvider.getInstance().appComponent.inject(this)
    }

    @Inject
    lateinit var usersDB: UsersDB

    override fun addUser(user: User): Long = Executors.newSingleThreadExecutor().submit(Callable {
        usersDB.getUsersDB().insertUser(user)
    }).get()

    override fun deleteUser(user: User) {
        //right now not needed
    }

    override fun updateUser(ID: String, newUser: User) {
        //right now not needed
    }

    override fun getUser(login: String): User? = Executors.newSingleThreadExecutor().submit(Callable {
        usersDB.getUsersDB().getUser(login)
    }).get()

}
