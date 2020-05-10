package com.aditya.istartest.controllers;

import com.aditya.istartest.models.fakultas.Fakultas;
import com.aditya.istartest.models.fakultas.FakultasService;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

/* 
    OK, Security is a very very complex aspect of sotfware development
    you should not just use 1 day to implement a proper authentication flow
    this class is just a very humble basic example on how an RBAC
    authentication and authorization work.

    JWT on the other hand is created to implement ABAC flow which usually used
    as inter microservice authentication method and NOT public authentication
    method.
*/

@Controller
public class LoginController {

	@PostMapping("/login")
	public ResponseEntity<?> get(@RequestParam Map<String, String> login, HttpServletResponse response) {
		if(login.get("username").equals("admin") && login.get("password").equals("admin")) {
            Cookie cookie = new Cookie("login_token","admin");
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/view/mahasiswa?page=1");
            response.addCookie(cookie);
            
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/");
            return new ResponseEntity<>(headers, HttpStatus.FOUND);
        }
    }
    
    public static Boolean authenticate(Cookie[] cookies) throws Exception {
        Boolean isCredsExist = false;
        String login_token = new String();

        for(Cookie cookie : cookies) {
            if(cookie.getName().equals("login_token")) {
                login_token = cookie.getValue();
                isCredsExist = true;
                break;
            }
        }

        if(isCredsExist && login_token.equals("admin")) {
            return true;
        } else {
            throw new Exception("Must login");
        }
    }
}