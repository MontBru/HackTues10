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
//    public JwtAuthenticationFilter(JwtService jwtService) {
//        this.jwtService = jwtService;
//    }
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
                    if(request.getRequestURI().equals("/user/getMe")
                            || request.getRequestURI().equals("/user/addFavorite")
                            || request.getRequestURI().equals("/user/removeFavorite")
                            || request.getRequestURI().equals("/user/addHistory")
                            || request.getRequestURI().equals("/user/removeHistory"))
                    {
                        System.out.println(userDetails.toString());
                        request.setAttribute("me", userDetails);
                    }
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
                }

                filterChain.doFilter(request, response);
//            }catch (RuntimeException error){
//                throw new RuntimeException("Unauthorized");
//            }
        }
    }
//        } catch (Exception error) {
//            // Create a JSON error response
//            ErrorResponse errorResponse = new ErrorResponse("Unauthorized", error.getMessage());
//            String jsonResponse = objectMapper.writeValueAsString(errorResponse);
//
//            // Send the JSON error response
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.setContentType("application/json");
//            response.getWriter().write(jsonResponse);
//            response.getWriter().flush();
//            response.getWriter().close();
//            return;
//        }
//    }
//
//    // Custom error response class
//    public class ErrorResponse {
//        private String error;
//        private String message;
//
//        public ErrorResponse(String error, String message) {
//            this.error = error;
//            this.message = message;
//        }
//
//        public String getError() {
//            return error;
//        }
//
//        public void setError(String error) {
//            this.error = error;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//        // Constructor, getters, and setters
//    }
