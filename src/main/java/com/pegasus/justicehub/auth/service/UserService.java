package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List findAll();
}
