package org.fitlink.trackingservice.Dto;

import java.util.List;

public record  CreteWorkoutRequest(String name , String  workoutType , String trainerId, String description , String difficultyLevel, Integer durationMinutes ,
                                   List<ExerciseRequest> exersices) {
}
