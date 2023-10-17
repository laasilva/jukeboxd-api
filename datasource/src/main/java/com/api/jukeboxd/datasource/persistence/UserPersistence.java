package com.api.jukeboxd.datasource.persistence;

import com.api.jukeboxd.core.exception.ValidationException;
import com.api.jukeboxd.core.exception.model.ErrorMessage;
import com.api.jukeboxd.core.model.Role;
import com.api.jukeboxd.core.model.User;
import com.api.jukeboxd.core.port.persistence.UserPersistenceAdapter;
import com.api.jukeboxd.datasource.mapper.UserInfoMapper;
import com.api.jukeboxd.datasource.mapper.UserMapper;
import com.api.jukeboxd.datasource.repository.UserInfoRepository;
import com.api.jukeboxd.datasource.repository.UserRepository;
import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPersistence implements UserPersistenceAdapter {
    private final UserRepository repository;
    private final UserInfoRepository userInfoRepository;
    private final UserMapper mapper;
    private final UserInfoMapper userInfoMapper;
    @Override
    public User fetchByUsername(String username) {
        return mapper.toModel(repository.findByUsername(username));
    }

    @Override
    public Boolean usernameExists(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public Boolean emailExists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public User addNewUser(User user) {
        try {
            var entity = mapper.toEntity(user);
            entity.setRole(Role.USER);
            return mapper.toModel(repository.save(entity));
        } catch (ValidationException e) {
            throw new ValidationException(ErrorMessage.EMAIL_EXISTS.getCode(), e.getMessage());
        }
    }

    @Override
    public UserDetails authByUsername(String username) {
        return repository.findByUsername(username);
    }
}
