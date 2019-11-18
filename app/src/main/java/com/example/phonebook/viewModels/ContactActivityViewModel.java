package com.example.phonebook.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class ContactActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> build = new MutableLiveData<>();

    public ContactActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<String> getBuild() {
        return build;
    }

    public void init(String toDo) {
        switch(toDo) {
            case "view":
                build.postValue("view");
                break;
            case "create":
                build.postValue("create");
                break;
        }
    }
}
