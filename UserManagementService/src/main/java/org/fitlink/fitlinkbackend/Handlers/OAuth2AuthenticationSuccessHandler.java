package org.fitlink.fitlinkbackend.Handlers;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.fitlink.fitlinkbackend.Exceptions.UserNotFoundException;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.fitlink.fitlinkbackend.Security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class Oauth2AuthenticationSuccessHandler  implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AppUserRepository userRepository;



    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User)  authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        AppUser user = userRepository.findByEmail(email).orElseThrow(() ->  new UserNotFoundException("user not found "));
        String token = jwtUtils.generateToken(user);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "{ \"token\": \"" + token + "\", " +
                        "\"email\": \"" + user.getEmail() + "\", " +
                        "\"name\": \"" + user.getName() + "\", " +
                        "\"role\": \"" + user.getUserRole().name() + "\" }"
        );

    }
}
