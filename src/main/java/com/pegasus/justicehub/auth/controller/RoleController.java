package com.pegasus.justicehub.auth.controller;

import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @GetMapping(value="/roles")
    public ModelAndView getRoles(){

        ModelAndView mv = new ModelAndView("roles");
        List<Role> roles = roleServiceImpl.findAll();
        mv.addObject("role_list", roles);
        return mv;
    }

    @PostMapping(value = "/roles")
    public String postRole(Role role){
        roleServiceImpl.save(role);
        return "redirect:/roles";
    }

    @DeleteMapping(value="/roles/{id}")
    public String deleteRole(@PathVariable("id") long id){
        Role role = roleServiceImpl.findById(id);
        roleServiceImpl.delete(role);
        return "redirect:/roles";
    }


}
