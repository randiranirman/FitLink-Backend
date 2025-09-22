package org.fitlink.trackingservice.Dto;

import org.fitlink.trackingservice.Models.Exersice;

import java.util.List;

public record CreateWorkOutResponse(String name , String  workoutType , String trainerId, String description , String difficultyLevel, Integer durationMinutes ,
                                    List<Exersice> exersicesList) {
}
