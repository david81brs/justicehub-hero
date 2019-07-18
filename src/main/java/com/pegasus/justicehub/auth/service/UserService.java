package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    List findAll();
}
