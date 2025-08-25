package org.fitlink.fitlinkbackend.Service;

import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Models.AppUserRole;
import org.fitlink.fitlinkbackend.Models.CustomOauth2User;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class CustomOAuth2UserService  extends DefaultOAuth2UserService {
    @Autowired
    private AppUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");
        String googleId = oauth2User.getAttribute("sub");

        // Check if user exists
        AppUser user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    // Create new user
                    AppUser newUser = new AppUser();
                    newUser.setEmail(email);
                    newUser.setName(name);
                    newUser.setUsername(email); // Use email as username
                    newUser.setFromOauth(true);
                    newUser.setEnabled(true);
                    newUser.setUserRole(AppUserRole.CLIENT);
                    // No password for OAuth users
                    return userRepository.save(newUser);
                });

        return new CustomOauth2User(oauth2User, user);
    }




}
