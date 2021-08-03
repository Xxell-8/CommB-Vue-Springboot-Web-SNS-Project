package com.ssafy.commb.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Route Controller
@Controller
@RequestMapping("/route")
public class RouteController implements ErrorController {

    @GetMapping("/index")
    public String redirectIndex(){
        return "index.html";
    }
}