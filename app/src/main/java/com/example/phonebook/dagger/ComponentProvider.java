package com.example.phonebook.dagger;

import android.app.Application;

import com.example.phonebook.dagger.components.AppComponent;
import com.example.phonebook.dagger.components.DaggerAppComponent;
import com.example.phonebook.dagger.components.ActivityComponent;
import com.example.phonebook.dagger.modules.AppModule;

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
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public ActivityComponent addActivityComponent() {
        if(activityComponent == null)
            activityComponent = appComponent.addActivityComponent();
        return activityComponent;
    }

    public void removeActivityComponent(){
        activityComponent = null;
    }
}
