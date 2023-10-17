package com.api.jukeboxd.core.port.service;

import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.exception.ValidationException;
import com.api.jukeboxd.core.model.User;

public interface UserSerivceAdapter {
    User fetchByUsername(String username);
    Boolean usernameExists(String username);
    Boolean emailExists(String email);
    User addNewUser(User user) throws PasswordEncryptionException, ValidationException;
}
