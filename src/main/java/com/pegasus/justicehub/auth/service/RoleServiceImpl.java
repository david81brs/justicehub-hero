package com.pegasus.justicehub.auth.service;

import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List findAll(){
        return roleRepository.findAll();
    }

    @Override
    public Role findById(long id){
        return roleRepository.findById(id);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

}
