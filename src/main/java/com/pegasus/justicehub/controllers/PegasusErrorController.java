package com.pegasus.justicehub.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PegasusErrorController  implements ErrorController {

    @RequestMapping("/error")
    @ResponseBody
    public ModelAndView handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("statusCode", statusCode);
        mv.addObject("exception", exception == null ? "N/A":exception.getMessage());

        return mv;
    }
    @Override
    public String getErrorPath() {
        return "/error";
    }
}

