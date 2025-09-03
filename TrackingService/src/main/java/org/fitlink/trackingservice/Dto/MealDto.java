package org.fitlink.trackingservice.Dto;

import jakarta.validation.constraints.NotBlank;
import org.fitlink.trackingservice.Models.FoodItem;

import java.util.List;

public record MealDto(@NotBlank String name , @NotBlank  String time , @NotBlank List<String> FoodItemIds) {

}
