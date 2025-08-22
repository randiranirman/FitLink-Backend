package org.fitlink.fitlinkbackend.Models;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;

@Document(collection = "app_users")
@Getter
@Setter



public class AppUser implements UserDetails {

    public AppUser() {
        this.userRole = AppUserRole.CLIENT; // Set default role
        this.createdAt = Instant.now();
        this.enabled = true;
    }
    
    public AppUser(String id, String email, String hashedPassword, String name, 
                   AppUserRole userRole, boolean enabled, boolean fromOauth, String username) {
        this.Id = id;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.name = name;
        this.userRole = userRole != null ? userRole : AppUserRole.CLIENT;
        this.enabled = enabled;
        this.fromOauth = fromOauth;
        this.username = username;
        this.createdAt = Instant.now();
    }


    @Id
    private String  Id;
    @Indexed(  unique = true)
    private String email ;
    private String hashedPassword ;
    private String name   ;
    private AppUserRole userRole;
    private boolean enabled= true;
    private boolean fromOauth = false ;
    private String  username ;


    private Instant createdAt = Instant.now();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userRole == null) {
            return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENT"));
        }
        return Collections.singletonList( new SimpleGrantedAuthority("ROLE_" + userRole.name()));
    }

    @Override
    public String getPassword() {
        return hashedPassword;
    }

    @Override
    public String getUsername() {
        return  username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
