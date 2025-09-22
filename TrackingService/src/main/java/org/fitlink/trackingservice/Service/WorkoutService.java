package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.CreateWorkOutResponse;
import org.fitlink.trackingservice.Dto.CreateWorkoutRequest;
import org.fitlink.trackingservice.Exception.ClientNotFound;
import org.fitlink.trackingservice.Exception.TrainerNotFoundException;
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
    private final UserService userService;




    private final WorkoutFetchingService workoutFetchingService;


    public CreateWorkOutResponse createWorkOut (CreateWorkoutRequest  request) {


         var newWorkOut = new Workout();
         if( userService.getClientByID(request.trainerId()) == null) {
              throw  new TrainerNotFoundException("trainer not found by that id " +  request.trainerId() );



         }


         newWorkOut.setName(request.name());
         newWorkOut.setDescription(request.description());
         newWorkOut.setWorkoutType(request.workoutType());

         newWorkOut.setTrainerId(request.trainerId());
         newWorkOut.setDurationMinutes(request.durationMinutes());



         // convert exercise request to  actual exercises
        if (request.exerciseName() != null) {
             var exercises = workoutFetchingService.fetchExercisesByName(request.exerciseName());

            newWorkOut.setExersices(exercises);
        }




        workoutRepository.save(newWorkOut);


        return new CreateWorkOutResponse(newWorkOut.getName(), newWorkOut.getWorkoutType(), newWorkOut.getTrainerId(), newWorkOut.getDescription()
        ,
                newWorkOut.getDifficultyLevel(),newWorkOut.getDurationMinutes(), newWorkOut.getExersices())
;




    }

    public List<Workout> getAllWorkOutsByTrainerId(String trainerID ) {

        if( userService.getClientByID(trainerID) == null) {
            throw  new ClientNotFound(" trainer not found  in that id " + trainerID);

        }



         var workouts = workoutRepository.findWorkoutByTrainerId(trainerID).stream().toList();


         return workouts;




    }
    public  List<Workout> getAllWorkouts ( ) {


          return workoutRepository.findAll().stream().toList();


    }




}
