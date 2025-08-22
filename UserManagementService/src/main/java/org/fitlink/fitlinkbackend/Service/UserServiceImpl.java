package org.fitlink.fitlinkbackend.Service;


import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl  implements UserDetailsService {
    @Autowired
     private AppUserRepository userRepository;
    @Override
    @Transactional


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException("User not found with email: " + email));
        return user;

    }
}
