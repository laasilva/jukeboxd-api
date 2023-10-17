package com.api.jukeboxd.core.port.persistence;

import com.api.jukeboxd.core.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserPersistenceAdapter {
    User fetchByUsername(String username);
    Boolean usernameExists(String username);
    Boolean emailExists(String email);
    User addNewUser(User user);
    UserDetails authByUsername(String username);
}
