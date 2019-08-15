package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {

    Role findById(long id);
    List<Role> findAll();
    void save(Role role);
}
