package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.model.User;

public interface UserPersistenceAdapter {
    User fetchByUsername(String username);
    Boolean usernameExists(String username);
    Boolean emailExists(String email);
    User addNewUser(User user);
}
