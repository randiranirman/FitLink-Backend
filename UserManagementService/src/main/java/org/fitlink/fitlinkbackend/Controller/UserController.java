package org.fitlink.fitlinkbackend.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.fitlink.fitlinkbackend.Service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor

@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final AdminService adminService;


    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<AppUser> getAllUsers() {

        var userlist =  adminService.getAllUsers();
        System.out.println(userlist);
        return userlist;

    }




}
