package com.api.jukeboxd.core.port.service;

import com.api.jukeboxd.core.model.AuthRequest;

public interface AuthServiceAdapter {
    String authenticate(AuthRequest auth);
    Boolean validate(String username);
}
