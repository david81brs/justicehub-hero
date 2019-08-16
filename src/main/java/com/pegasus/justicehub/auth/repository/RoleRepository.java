package com.pegasus.justicehub.auth.repository;

import com.pegasus.justicehub.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findById(long id);
    List<Role> findAll();
    void delete(Role role);
}
