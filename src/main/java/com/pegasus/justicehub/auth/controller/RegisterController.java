package com.pegasus.justicehub.auth.controller;


import com.nulabinc.zxcvbn.Strength;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.pegasus.justicehub.EmailService;
import com.pegasus.justicehub.auth.model.User;
import com.pegasus.justicehub.auth.service.UserService;
import com.pegasus.justicehub.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserServiceImpl userService;
    private EmailService emailService;

    @Autowired
    public RegisterController(BCryptPasswordEncoder bCryptPasswordEncoder, UserServiceImpl userService, EmailService emailService){
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(value="/register" , method = RequestMethod.GET)
    public ModelAndView showRegistrationPage(ModelAndView modelAndView, User user){
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ModelAndView processRegistrationForm(ModelAndView modelAndView, @Valid User user, BindingResult bindingResult, HttpServletRequest request){
        User userExists = userService.findByEmail(user.getEmail());
        System.out.println(userExists);

        if (userExists!=null){
            modelAndView.addObject("alreadyRegisteredMessage","Oopa! Já existe esse usuário");
            modelAndView.setViewName("register");
        }

        if (bindingResult.hasErrors()){

            modelAndView.setViewName("register");

        } else {
            user.setEnabled(false);
            user.setConfirmationToken(UUID.randomUUID().toString());
            userService.save(user);

            String appUrl = request.getScheme() + "://" + request.getServerName();

            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(user.getEmail());
            registrationEmail.setSubject("Confirmação de Registro PEGASUS");
            registrationEmail.setText("Para confirmar, clique no link abaixo\n" + appUrl + "/confirm?token=" + user.getConfirmationToken());
            registrationEmail.setFrom("noreply@justicehub.herokuapp.com");
            emailService.sendEmail(registrationEmail);

            modelAndView.addObject("confirmationMessage","Um e-mail de confirmação foi enviado para "+user.getEmail());

        }
        return modelAndView;
    }

    @RequestMapping(value="/confirm", method = RequestMethod.GET)
    public ModelAndView showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {

        User user = userService.findByConfirmationToken(token);

        if (user == null) { // No token found in DB
            modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
        } else { // Token found
            modelAndView.addObject("confirmationToken", user.getConfirmationToken());
        }

        modelAndView.setViewName("confirm");
        return modelAndView;
    }

//    @RequestMapping(value="/confirm", method = RequestMethod.POST)
//    public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
//
//        modelAndView.setViewName("confirm");
//
//        Zxcvbn passwordCheck = new Zxcvbn();
//
//        Strength strength = passwordCheck.measure(requestParams.get("password"));
//
//        if (strength.getScore() < 3) {
//            bindingResult.reject("password");
//
//            redir.addFlashAttribute("errorMessage", "Your password is too weak.  Choose a stronger one.");
//
//            modelAndView.setViewName("redirect:confirm?token=" + requestParams.get("token"));
//            System.out.println(requestParams.get("token"));
//            return modelAndView;
//        }
//
//        // Find the user associated with the reset token
//        User user = userService.findByConfirmationToken(requestParams.get("token"));
//
//        // Set new password
//        user.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));
//
//        // Set user to enabled
//        user.setEnabled(true);
//
//        // Save user
//        userService.save(user);
//
//        modelAndView.addObject("successMessage", "Your password has been set!");
//        return modelAndView;
//    }

}
