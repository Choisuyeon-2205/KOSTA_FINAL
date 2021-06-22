package com.kosta.finalProject.controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OAuthController {
	@GetMapping("/")
    public String index(){
        return "/index";
    }

    @GetMapping("/user")
    public String user(Principal principal){
        log.info("user name :: "+principal.getName());
        return "/user";
    }
}
