package com.pegasus.justicehub.auth.repository;

import com.pegasus.justicehub.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
