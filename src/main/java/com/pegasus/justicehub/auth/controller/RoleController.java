package com.pegasus.justicehub.auth.controller;

import com.pegasus.justicehub.auth.model.Role;
import com.pegasus.justicehub.auth.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Set;

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


}
