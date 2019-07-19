package com.pegasus.justicehub.auth.controller;

import com.pegasus.justicehub.auth.model.User;
import com.pegasus.justicehub.auth.repository.UserRepository;
import com.pegasus.justicehub.auth.service.SecurityService;
import com.pegasus.justicehub.auth.service.UserService;
import com.pegasus.justicehub.auth.service.UserServiceImpl;
import com.pegasus.justicehub.auth.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserRepository ur;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/v1/users")
    public ResponseEntity<List<User>> allUsers(){
        List<User> lu = userService.findAll();
        //ObjectMapper om = new ObjectMapper();

        return new ResponseEntity<List<User>>(lu, HttpStatus.OK);
    }

    @GetMapping("/users")
    public String admin(Model model){
        List<User> lu = userService.findAll();
        model.addAttribute("users", lu);
        return "users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id){
        User user = userService.findById(id);
        ur.delete(user);
        return "redirect:/users";
    }
}
