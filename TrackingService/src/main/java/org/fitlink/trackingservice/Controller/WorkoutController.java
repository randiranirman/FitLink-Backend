package org.fitlink.trackingservice.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.CreateWorkoutRequest;
import org.fitlink.trackingservice.Service.WorkoutService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/workouts"
)
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;




    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create-worout")


    public void createWorkOut(@RequestBody CreateWorkoutRequest request){


        workoutService.createWorkOut(request);
    }
}
