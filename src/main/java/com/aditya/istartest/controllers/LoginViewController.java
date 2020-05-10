package com.aditya.istartest.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

import javax.annotation.PostConstruct;

@Controller
public class LoginViewController {
    ResponseStatusException stdException;

    @PostConstruct
    public void init() throws Exception {
        this.stdException = new ResponseStatusException(
            HttpStatus.BAD_GATEWAY,
            "Something wrong happened"
        );
    }

    @GetMapping("/")
    String mahasiswa(
        Model model,
        @CookieValue(name = "login_token", defaultValue = "none") String creds) {
        try {
            if(!creds.equals("none")) {
                return "redirect:/view/mahasiswa?page=1";
            } else {
                return "login";
            }
        } catch (Exception e ) {
            e.printStackTrace();
            throw this.stdException;
        }
    }
}