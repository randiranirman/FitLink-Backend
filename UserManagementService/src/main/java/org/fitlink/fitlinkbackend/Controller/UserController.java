package org.fitlink.fitlinkbackend.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.UserDto;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final AdminService adminService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {

        var userlist =  adminService.getAllUsers();
        System.out.println(userlist);
        return userlist;

    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserByID( @PathVariable String id ) {

        return adminService.findUserById(id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserById( @PathVariable String id) {

        adminService.deleteUserById(id);

    }

    @GetMapping("/user/clients")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllClients() {

        var clients  = adminService.getAllClients().stream().map( user -> new UserDto(user.getEmail(), user.getName(), user.getId(), user.getUserRole())).toList();
        return clients;

    }

    @GetMapping("/user/trainers")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllTrainers() {

        var clients  = adminService.getAllTrainers().stream().map( user -> new UserDto(user.getEmail(), user.getName(), user.getId(), user.getUserRole())).toList();
        return clients;

    }




}
