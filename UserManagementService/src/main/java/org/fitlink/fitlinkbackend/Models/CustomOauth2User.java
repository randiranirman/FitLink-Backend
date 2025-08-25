package org.fitlink.fitlinkbackend.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Collection;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor

public class CustomOauth2User implements OAuth2User
{

    private OAuth2User oAuth2User;
    private AppUser appUser;


    @Override
    public Map<String, Object> getAttributes() {
        return oAuth2User.getAttributes();

    }

@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return appUser
                .getAuthorities();
    }

    @Override
    public String getName() {
        return oAuth2User.getName();
    }

}
