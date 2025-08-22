package org.fitlink.fitlinkbackend.Service;


import org.fitlink.fitlinkbackend.Dto.AuthResponse;
import org.fitlink.fitlinkbackend.Dto.LoginRequest;
import org.fitlink.fitlinkbackend.Dto.RegisterRequest;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.fitlink.fitlinkbackend.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService

{


    @Autowired
     private AppUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse register (RegisterRequest request) {
        // validate  passsword matches or not
        if (!request.password().equals(request.confirmPassword())) {
            throw new RuntimeException("Passwords do not match");

        }
        // check if the user already exits
        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("User already exists with email: " + request.email());
        }
        // create a new user
        AppUser user = new AppUser();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setUserRole(request.appUserRole());
        user.setHashedPassword(passwordEncoder.encode(request.password()));
        user.setUsername( request.username());

        userRepository.save(user);
        //  generate jwt token
        String jwt = jwtUtils.generateToken(user);

        return new AuthResponse(jwt, user.getEmail(), user.getName(), user.getUserRole().toString());

    }
    public AuthResponse login(LoginRequest request) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AppUser userDetails = ( AppUser)  authentication.getPrincipal();
        String jwt = jwtUtils.generateToken(userDetails);
        return new AuthResponse(jwt, userDetails.getEmail(), userDetails.getName(), userDetails.getUserRole().toString());
    }





}
