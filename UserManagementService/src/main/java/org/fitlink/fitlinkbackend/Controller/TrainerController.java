package org.fitlink.fitlinkbackend.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.ClientDetialsDto;
import org.fitlink.fitlinkbackend.Service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trainer")
public class TrainerController {
    @Autowired

    private final TrainerService trainerService;

    @GetMapping("/getClientDetails/{trainerId}")
    @ResponseStatus(HttpStatus.OK)


    public List<ClientDetialsDto> getClientDetailsByTrainerID (@PathVariable String trainerId) {


        return trainerService.getRegisterdCientsByTrainerId(trainerId);






    }



}
