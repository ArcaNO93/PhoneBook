package com.example.phonebook.utils;

import android.app.Application;

import com.example.phonebook.dagger.components.AppComponent;
import com.example.phonebook.dagger.components.ActivityComponent;
import com.example.phonebook.dagger.components.DaggerAppComponent;
import com.example.phonebook.dagger.modules.ContactModule;
import com.example.phonebook.dagger.modules.ReposModule;
import com.example.phonebook.dagger.modules.UserModule;


import org.jetbrains.annotations.Contract;

public class ComponentProvider extends Application {

    protected static ComponentProvider componentProvider;
    private AppComponent appComponent;
    private ActivityComponent activityComponent;

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

    public ActivityComponent addActivityComponent() {

        if(activityComponent == null)
            activityComponent = appComponent
                    .activityComponentBuilder()
                    .reposModule(new ReposModule())
                    .contactsModule(new ContactModule())
                    .userModule(new UserModule())
                    .build();

        return activityComponent;
    }

        public void removeActivityComponent(){
        activityComponent = null;
    }
}
