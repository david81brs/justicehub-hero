package com.pegasus.justicehub.auth.repository;

import com.pegasus.justicehub.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    User findByUsername(String name);
    User findByEmail(String email);
    User findByConfirmationToken(String confirmationToken);
}
