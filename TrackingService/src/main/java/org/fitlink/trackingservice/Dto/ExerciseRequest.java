package org.fitlink.trackingservice.Dto;

public record ExerciseRequest(String name , String type , String muscle , String equipment , String difficulty , Integer sets, Integer reps, Integer restSeconds ) {
}
