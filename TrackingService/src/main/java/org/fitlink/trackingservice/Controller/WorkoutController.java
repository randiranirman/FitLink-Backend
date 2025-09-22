package org.fitlink.trackingservice.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.CreateWorkOutResponse;
import org.fitlink.trackingservice.Dto.CreateWorkoutRequest;
import org.fitlink.trackingservice.Models.Workout;
import org.fitlink.trackingservice.Service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts"
)
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;




    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-worout")


    public CreateWorkOutResponse createWorkOut(@RequestBody CreateWorkoutRequest request){


        return workoutService.createWorkOut(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Workout> getAllWorkouts( ) {

        return workoutService.getAllWorkouts();



    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/workouts/{trainerId}")
    public List<Workout> getWorkOutsByTrainerId( @PathVariable  String trainerId) {




        return workoutService.getAllWorkOutsByTrainerId(trainerId);





    }

}
