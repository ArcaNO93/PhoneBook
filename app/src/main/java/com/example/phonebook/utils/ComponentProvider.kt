package com.example.phonebook.utils

import android.app.Application

import com.example.phonebook.dagger.components.AppComponent
import com.example.phonebook.dagger.components.AuthActViewModelsComponent
import com.example.phonebook.dagger.components.DaggerAppComponent
import com.example.phonebook.dagger.components.MainActViewModelsComponent

class ComponentProvider : Application() {

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
                .builder()
                .withApplication(this)
                .build()
    }

    companion object {
        private var appComponent: AppComponent? = null
        private var authActViewModelsComponent: AuthActViewModelsComponent? = null
        private var mainActViewModelsComponent: MainActViewModelsComponent? = null

        fun getAppComponent() :AppComponent? {
            return appComponent
        }

        fun addAuthActViewModelsComponent() : AuthActViewModelsComponent? {
            return appComponent?.authActViewModelsComponent()?.build()
        }

        fun removeAuthActViewModelsComponent() {
            authActViewModelsComponent = null
        }

        fun addMainActViewModelsComponent(): MainActViewModelsComponent? {
            return appComponent?.mainActViewModelsComponent()?.build()
        }

        fun removeMainActViewModelsComponent() {
            mainActViewModelsComponent = null
        }
    }
}
