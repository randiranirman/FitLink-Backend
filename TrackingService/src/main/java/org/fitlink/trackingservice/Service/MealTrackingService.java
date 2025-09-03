package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;

import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Repository.FoodItemRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor


public class MealTrackingService {


    private final  NutritionService nutritionService;
    private final FoodItemRepository foodItemRepository;

    public FoodItem createFoodItem (String name ,  double quantity , String unit ) {

var item = nutritionService.getNutrition(name ,quantity, unit);
foodItemRepository.save(item);

     return  item ;

    }



    public List<FoodItem> getFoodItems() {

         var foodItems = foodItemRepository.findAll();

         return foodItems;



    }







}
