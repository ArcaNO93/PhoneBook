package com.example.phonebook.model.repos;

interface ServiceRepo {
    void setCurrentUser(String currentUser);
    void setSignedUp(boolean flag);
    String getCurrentUser();
    boolean getSignedUp();
}
