package org.fitlink.fitlinkbackend.Service;


import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.UserDto;
import org.fitlink.fitlinkbackend.Exceptions.UserNotFoundException;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final AppUserRepository userRepository;

    public List<UserDto> getAllUsers ( ) {


        var userList =  userRepository.findAll().stream().map(  user ->  new UserDto(user.getEmail(), user.getName(), user.getId(), user.getUserRole())).toList();

        if( userList.isEmpty()) {
             return null;

        }

        return userList;


    }

    public UserDto findUserById( String id) {

        Predicate<AppUser> userPredicate= user -> user.getId().equals(id );

        var findUser = userRepository.findAll().stream().filter(userPredicate).map(user -> new UserDto(user.getEmail(), user.getName(), user.getId(),  user.getUserRole())).findFirst().orElseThrow(() -> new  UserNotFoundException("User not found with id: " + id));


        return findUser;





    }







}
