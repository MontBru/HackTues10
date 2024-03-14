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
////        var user = User.builder()
////                .firstname(request.getFirst_name())
////                .lastname(request.getLast_name())
////                .email(request.getEmail())
////                .password(passwordEncoder.encode(request.getPassword()))
////                .role(Role.USER)
////                .build();
//
//        User user = new User(request.getUsername(),request.getEmail(),
//                passwordEncoder.encode(request.getPassword()),
//                request.getDescription(),
//                request.getRating(),Role.USER);
//        repository.save(user);
//        for(SubjectDto subject : request.getTags())
//        {
//            System.out.println(subject.toString());
//            Subject newSubject = subjectService.getByName(subject.getName());
//            userService.addSubject(newSubject, user);
//        }
//        Collection<FavoriteOrHistoryUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
//            return new FavoriteOrHistoryUserDto(favorite.getUsername(),
//                    favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
//        }).toList();
//        Collection<FavoriteOrHistoryUserDto> history = user.getHistory().stream().map((historyUser) -> {
//            return new FavoriteOrHistoryUserDto(historyUser.getUsername()
//                    ,historyUser.getEmail(), historyUser.getTags(),
//                    historyUser.getDescription(), historyUser.getRating());
//        }).toList();
//        MeUserDto me = new MeUserDto(user.getUsername(), user.getEmail(), user.getTags(), favorites,history,
//                user.getDescription(), user.getRating());
//        var jwtToken = jwtService.generateToken(user);
//        return new AuthenticationResponse(jwtToken, me);
        System.out.println("alo da");
        MyUser newUser = new MyUser(request.getUsername(), request.getEmail(),
                request.getPassword(), request.getDevice_id(),
                1, request.getNumber(), null, null );
        repository.save(newUser);
        var jwtToken = jwtService.generateToken(newUser);
        return new AuthenticationResponse(jwtToken, newUser);
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
//        System.out.println(request.toString());
//
////        authenticationManager.authenticate(
////                new UsernamePasswordAuthenticationToken(request.getEmail(),
////                        request.getPassword())
////        );
//        try {
//            User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//
//
//            var jwtToken = jwtService.generateToken(user);
//            Collection<FavoriteOrHistoryUserDto> favorites = user.getFavorites().stream().map((favorite) -> {
//                return new FavoriteOrHistoryUserDto(favorite.getUsername(), favorite.getEmail(), favorite.getTags(), favorite.getDescription(), favorite.getRating());
//            }).toList();
//            Collection<FavoriteOrHistoryUserDto> history = user.getHistory().stream().map((historyUser) -> {
//                return new FavoriteOrHistoryUserDto(historyUser.getUsername(),historyUser.getEmail(), historyUser.getTags(), historyUser.getDescription(), historyUser.getRating());
//            }).toList();
//            MeUserDto me = new MeUserDto(user.getUsername(), user.getEmail(), user.getTags(), favorites,history, user.getDescription(), user.getRating());
//            System.out.println("the me : "  + me.getUsername());
//            return new AuthenticationResponse(jwtToken, me);
//        }catch(Exception error)
//        {
//            throw new AuthenticationException("This email is already in use");
//        }

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
