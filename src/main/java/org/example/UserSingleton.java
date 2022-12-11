package org.example;

import org.example.Entity.User;

public enum UserSingleton {
    INSTANCE;
    User user;

    public User get(){
        if (user == null) {
            user = new User();
        }
        return user;
    }
}
