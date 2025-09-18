package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.CreateWorkoutRequest;
import org.fitlink.trackingservice.Models.Exersice;
import org.fitlink.trackingservice.Models.Workout;
import org.fitlink.trackingservice.Repository.WorkoutRepository;
import org.fitlink.trackingservice.Utils.WorkoutFetchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class WorkoutService {


    private  final WorkoutRepository workoutRepository;



    private final WorkoutFetchingService workoutFetchingService;


    public Workout createWorkOut (CreateWorkoutRequest  request) {


         var newWorkOut = new Workout();

         newWorkOut.setName(request.name());
         newWorkOut.setDescription(request.description());
         newWorkOut.setWorkoutType(request.workoutType());
         newWorkOut.setTrainerId(request.trainerId());
         newWorkOut.setDurationMinutes(request.durationMinutes());


         // convert exercise request to  actual exercises
        if (request.exerciseName() != null) {
            List<Exersice> exercises = workoutFetchingService.fetchExercisesByName(request.exerciseName());

            newWorkOut.setExersices(exercises);
        }

        workoutRepository.save(newWorkOut);


        return  newWorkOut;




    }




}
