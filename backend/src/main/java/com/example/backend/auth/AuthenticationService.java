package com.example.backend.auth;


import com.example.backend.Classes.MyUser;
import com.example.backend.Classes.Subclass;
import com.example.backend.DTO.MyUserDTO;
import com.example.backend.Repositories.SubClassRepository;
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
import java.util.List;

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
    private SubClassRepository subClassRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request)
    {
        List<Subclass> subclassList = request.getClasses().stream().map((c) ->{
            return subClassRepository.findByKlasAndGrade(c.getKlas(), c.getGrade()).orElse(null);
        }).toList();
        MyUser newUser = new MyUser(request.getUsername(), request.getEmail(),
        passwordEncoder.encode(request.getPassword()), request.getDevice_id(),
                request.getRole(), request.getNumber(), null, subclassList );
        repository.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);
        MyUserDTO myUser = new MyUserDTO(newUser.getName(), newUser.getRole());
        return new AuthenticationResponse(jwtToken, myUser);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {


        MyUser me = (MyUser) repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if(me != null && passwordEncoder.matches(request.getPassword(), me.getPassword())) {
            var jwtToken = jwtService.generateToken(me);
            MyUserDTO myUser = new MyUserDTO(me.getName(), me.getRole());

            return new AuthenticationResponse(jwtToken, myUser);
        }
        else{
            throw new UsernameNotFoundException("The password is wrong");
        }

    }

}
