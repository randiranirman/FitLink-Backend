package org.fitlink.fitlinkbackend.Service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final AppUserRepository userRepository;






    public List<AppUser> getAllUsers( ) {

         return  userRepository.findAll();
    }
}
