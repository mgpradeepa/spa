package com.mgp.web.appmods.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

public class AuthController {

    @GetMapping("/")
    public String index(Principal principal){
        return principal.getName();
    }
}
