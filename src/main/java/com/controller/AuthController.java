package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JwtHelper;
import com.dao.customUser;
import com.dao.customUserDao;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private customUserDao customUserDao;


    @Autowired
    private JwtHelper helper;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User request) {

        this.doAuthenticate(request.getName(), request.getPassword());
        
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getName());
        String token = this.helper.generateToken(userDetails); 

        Map<String, String> response= new HashMap<>();
        
        response.put("token", token);
        response.put("username", userDetails.getUsername());
          
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody customUser request) {
	
    	request.setUsername(request.getUsername());
    	request.setPassword(request.getPassword());
    	request.setNumber(request.getNumber());

        customUserDao.save(request);
        
        return new ResponseEntity<>("User saved", HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
