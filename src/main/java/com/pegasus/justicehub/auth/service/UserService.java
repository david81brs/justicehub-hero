package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    void save(User user);

    User findById(long id);

    User findByUsername(String username);

    User findByEmail(String email);

    User findByConfirmationToken(String confirmationToken);

    List findAll();

    Set<Role> getRoles(long id);

    void addRole(long user_id, long role_id);

    void removeRole(long user_id, long role_id);
}
