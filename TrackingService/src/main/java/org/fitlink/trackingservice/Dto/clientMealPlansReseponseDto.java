package org.fitlink.trackingservice.Dto;

import org.fitlink.trackingservice.Models.MealPlan;

import java.time.LocalDate;
import java.util.List;

public record clientMealPlansReseponseDto(LocalDate startDate  , LocalDate endDate , List<MealPlan> mealPlans) {

}
