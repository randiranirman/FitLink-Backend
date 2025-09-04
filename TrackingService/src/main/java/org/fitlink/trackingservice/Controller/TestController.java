package org.fitlink.trackingservice.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.MealPlanRequest;
import org.fitlink.trackingservice.Dto.UserDto;
import org.fitlink.trackingservice.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")

@RequiredArgsConstructor

public class TestController {
    private final UserService userService;
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)



    public UserDto getClientByID(@PathVariable  String id){


        var client = userService.getClientByID(id);


        return client;




    }

    @GetMapping("/api/test/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public String  createMealPlan(@RequestParam String clientId , @RequestBody MealPlanRequest request) {


        return "Hello World";

    }



}
