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

    @Autowired
    private  final WorkoutRepository workoutRepository;

    @Autowired

    private final WorkoutFetchingService workoutFetchingService;


    public Workout createWorkOut (CreateWorkoutRequest  request) {


         var newWorkOut = new Workout();

         newWorkOut.setName(request.name());
         newWorkOut.setDescription(request.description());
         newWorkOut.setWorkoutType(request.workoutType());
         newWorkOut.setTrainerId(request.trainerId());
         newWorkOut.setDurationMinutes(request.durationMinutes());


         // convert exercise request to  actual exercises
        if (request.exercises() != null) {
            List<Exersice> exercises = request.exercises().stream()
                    .map(exerciseReq -> {
                        Exersice exercise = new Exersice();
                        exercise.setName(exerciseReq.name());
                        exercise.setType(exerciseReq.type());
                        exercise.setMuscle(exerciseReq.muscle());
                        exercise.setEquipment(exerciseReq.equipment());
                        exercise.setDifficulty(exerciseReq.difficulty());
                        exercise.setSets(exerciseReq.sets());
                        exercise.setReps(exerciseReq.reps());
                        exercise.setRestSeconds(exerciseReq.restSeconds());
                        return exercise;
                    })
                    .toList();
            newWorkOut.setExersices(exercises);
        }


        return  newWorkOut;




    }



}
