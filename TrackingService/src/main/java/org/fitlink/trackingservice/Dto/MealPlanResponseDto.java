package org.fitlink.trackingservice.Dto;

import org.fitlink.trackingservice.Models.Meal;

import java.time.LocalDate;
import java.util.List;

public record MealPlanResponseDto(String clientId , LocalDate startDate, LocalDate endDate , List<Meal> meals) {
}
