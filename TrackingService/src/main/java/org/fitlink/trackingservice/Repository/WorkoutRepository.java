package org.fitlink.trackingservice.Repository;

import org.fitlink.trackingservice.Models.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface WorkoutRepository  extends MongoRepository<Workout, String> {
    List< Workout>  findWorkoutByTrainerId( String trainerId);
    List<Workout> findWorkoutByWorkoutType(String workoutType)
;
    List<Workout> findWorkoutByTrainerIdAndWorkoutType(String trainerId , String  workoutType );
    List<Workout>  findByIsCompleted();
    

}
