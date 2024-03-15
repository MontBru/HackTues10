package com.example.backend.configuration;



import com.example.backend.Classes.MyUser;
import com.example.backend.Services.UserService;
import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {
//            try {
                final String authHeader = request.getHeader("Authorization");
                final String jwt;
                final String userEmail;
                System.out.println(authHeader);
                if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                jwt = authHeader.substring(7);
                System.out.println(jwt);
                System.out.println(authHeader);
                userEmail = jwtService.extractUsername(jwt);
                if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    MyUser userDetails = (MyUser) userService.getByEmail(userEmail).orElse(null);
                   if(request.getRequestURI().equals("/user/getMe"))
                   {
                        request.setAttribute("me", userDetails);
                   }
                    if(request.getRequestURI().equals("/user/isTokenValid"))
                    {
                        request.setAttribute("isAuthenticated", jwtService.isTokenValid(jwt, userDetails));
                    }
                    try{
                        if (jwtService.isTokenValid(jwt, userDetails)) {

                            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                            authToken.setDetails(
                                    new WebAuthenticationDetailsSource().buildDetails(request)
                            );
                            SecurityContextHolder.getContext().setAuthentication(authToken);

                        }
                        System.out.println(jwtService.isTokenValid(jwt, userDetails));


                        filterChain.doFilter(request, response);
                    }catch(Exception e){
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    }
                }

        }
    }
