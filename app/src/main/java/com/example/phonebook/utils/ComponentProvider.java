package com.example.phonebook.utils;

import android.app.Application;

import com.example.phonebook.dagger.components.AppComponent;
import com.example.phonebook.dagger.components.AuthComponent;
import com.example.phonebook.dagger.components.DaggerAppComponent;
import com.example.phonebook.dagger.components.MainComponent;

import org.jetbrains.annotations.Contract;

public class ComponentProvider extends Application {

    protected static ComponentProvider componentProvider;
    private AppComponent appComponent;
    private AuthComponent authComponent;
    private MainComponent mainComponent;

    @Contract(pure = true)
    public static ComponentProvider getInstance() {
        return componentProvider;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        componentProvider = this;

        appComponent = DaggerAppComponent
                .builder()
                .withApplication(this)
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public AuthComponent addAuthComponent() {

        if(authComponent == null)
            authComponent = appComponent
                    .authComponent()
                    .build();

        return authComponent;
    }

    public void removeAuthComponent() {
        authComponent = null;
    }

    public MainComponent addMainComponent() {

        if(mainComponent == null)
            mainComponent = appComponent
                    .mainComponent()
                    .build();

        return mainComponent;
    }

    public void removeMainComponent() {
        mainComponent = null;
    }
}
