package org.fitlink.fitlinkbackend.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.ClientRegisterForTrainerRequest;
import org.fitlink.fitlinkbackend.Dto.UserDto;
import org.fitlink.fitlinkbackend.Service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")

@RequiredArgsConstructor
public class ClientController {


    private final ClientService clientService;


    @PostMapping("/registerTrainer")
    @ResponseStatus(HttpStatus.OK)
    public void registerTrainer(@RequestParam String trainerId , @RequestBody ClientRegisterForTrainerRequest request) {

        clientService.registerForTrainer(trainerId, request);







    }

    @GetMapping("/searchTrainers")

    public List<UserDto> searchTrainer( @RequestParam String name) {


        return clientService.searchTrainerByName(name);

    }



}
