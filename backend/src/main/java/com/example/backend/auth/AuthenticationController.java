package com.example.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(service.register(request));
    };

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request)throws AuthenticationException
    {

        try{
            return ResponseEntity.ok(service.authenticate(request));
        }catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }

    };
}
