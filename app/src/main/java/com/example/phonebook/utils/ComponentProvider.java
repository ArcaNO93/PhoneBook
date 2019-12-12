package com.example.phonebook.utils;

import android.app.Application;

import com.example.phonebook.dagger.components.AppComponent;
import com.example.phonebook.dagger.components.AuthActViewModelsComponent;
import com.example.phonebook.dagger.components.DaggerAppComponent;
import com.example.phonebook.dagger.components.MainActViewModelsComponent;

import org.jetbrains.annotations.Contract;

public class ComponentProvider extends Application {

    protected static ComponentProvider componentProvider;
    private AppComponent appComponent;
    private AuthActViewModelsComponent authActViewModelsComponent;
    private MainActViewModelsComponent mainActViewModelsComponent;

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

    public AuthActViewModelsComponent addAuthActViewModelsComponent() {

        if(authActViewModelsComponent == null)
            authActViewModelsComponent = appComponent
                    .authActViewModelsComponent()
                    .build();

        return authActViewModelsComponent;
    }

    public void removeAuthActViewModelsComponent() {
        authActViewModelsComponent = null;
    }

    public MainActViewModelsComponent addMainActViewModelsComponent() {

        if(mainActViewModelsComponent == null)
            mainActViewModelsComponent = appComponent
                    .mainActViewModelsComponent()
                    .build();

        return mainActViewModelsComponent;
    }

    public void removeMainActViewModelsComponent() {
        mainActViewModelsComponent = null;
    }
}
