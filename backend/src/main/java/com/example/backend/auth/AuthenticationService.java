package com.example.backend.auth;


import com.example.backend.Classes.MyUser;
import com.example.backend.Repositories.UserRepository;
import com.example.backend.Services.SubjectServices;
import com.example.backend.Services.UserService;
import com.example.backend.configuration.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.naming.AuthenticationException;
import java.util.Collection;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectServices subjectService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {
        System.out.println("alo da");
        MyUser newUser = new MyUser(request.getUsername(), request.getEmail(),
        passwordEncoder.encode(request.getPassword()), request.getDevice_id(),
                1, request.getNumber(), null, null );
        repository.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);
        return new AuthenticationResponse(jwtToken, newUser);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {


        MyUser me = (MyUser) repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(me != null && passwordEncoder.matches(request.getPassword(), me.getPassword())) {
            var jwtToken = jwtService.generateToken(me);
            return new AuthenticationResponse(jwtToken, me);
        }
        else{
            throw new UsernameNotFoundException("The password is wrong");
        }

    }

}
