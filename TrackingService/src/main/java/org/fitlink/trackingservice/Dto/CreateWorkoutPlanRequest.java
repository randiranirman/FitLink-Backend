package org.fitlink.trackingservice.Dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateWorkoutPlanRequest(String clientId , String trainerId , String planName , String description , LocalDateTime startDate,LocalDateTime endDate) {

}
