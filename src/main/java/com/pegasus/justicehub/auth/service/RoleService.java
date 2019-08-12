package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.Role;

import java.util.List;

public interface RoleService {

    Role findById(long id);
    List findAll();
    void save(Role role);
}
