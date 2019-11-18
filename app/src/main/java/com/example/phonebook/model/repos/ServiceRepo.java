package com.example.phonebook.model.repos;

interface ServiceRepo {
    void setCurrentUser(String _currentUser);
    void setSignedUp(boolean _flag);
    String getCurrentUser();
    boolean getSignedUp();
}
