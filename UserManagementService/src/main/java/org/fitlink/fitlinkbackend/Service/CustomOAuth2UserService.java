package org.fitlink.fitlinkbackend.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor

public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final AppUserRepository appUserRepository;

    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

       OAuth2User  oAuth2User = super.loadUser(userRequest);

       String email = oAuth2User.getAttribute("email");
       String name = oAuth2User.getAttribute("name");
       // check if the user exists  if not create the user
        AppUser  user = appUserRepository.findByEmail(email).orElseGet(() -> {
            AppUser newUser = new AppUser();
            newUser.setEmail(email);
            newUser.setName(name);
            newUser.setFromOauth(true);
            newUser.setUsername(email);

            return appUserRepository.save(newUser);

        });

        return new DefaultOAuth2User(
                user.getAuthorities(),
                oAuth2User.getAttributes(),
                "email"
        );


    }

}
