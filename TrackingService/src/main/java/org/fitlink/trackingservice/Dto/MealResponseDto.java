package org.fitlink.trackingservice.Dto;

import org.fitlink.trackingservice.Models.FoodItem;

import java.util.List;

public record MealResponseDto(String id , String time , int totalCalories , int totalProteinContains , int totalFatContains , int totalCarbsContains , List<FoodItem> foodItems) {
}
