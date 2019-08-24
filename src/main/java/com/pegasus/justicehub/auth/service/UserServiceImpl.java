package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.model.User;
import com.pegasus.justicehub.auth.repository.RoleRepository;
import com.pegasus.justicehub.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<Role>();
        roles.add(roleRepository.findByName("ROLE_USER"));
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @Override
    public User findByConfirmationToken(String confirmationToken){
        return userRepository.findByConfirmationToken(confirmationToken);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(long id){
        return userRepository.findById(id);
    }

    @Override
    public Set<Role> getRoles(long id){
        User user = userRepository.findById(id);
        Set<Role> users_roles = user.getRoles();
        return users_roles;
    }
}
