package com.api.jukeboxd.core.business;

import com.api.jukeboxd.core.exception.PasswordEncryptionException;
import com.api.jukeboxd.core.exception.ValidationException;
import com.api.jukeboxd.core.model.User;
import com.api.jukeboxd.core.port.persistence.UserPersistenceAdapter;
import com.api.jukeboxd.core.port.service.UserSerivceAdapter;
import com.api.jukeboxd.core.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserService implements UserSerivceAdapter {
    private final UserPersistenceAdapter persistence;
    private final PasswordEncoder passwordEncoder;
    @Override
    public User fetchByUsername(String username) {
        return persistence.fetchByUsername(username);
    }

    @Override
    public Boolean usernameExists(String username) {
        return persistence.usernameExists(username);
    }

    @Override
    public Boolean emailExists(String email) {
        return persistence.emailExists(email);
    }

    @Override
    public User addNewUser(User user) throws ValidationException {
        PasswordUtil.verifyValidPassword(user.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return persistence.addNewUser(user);

    }
}
