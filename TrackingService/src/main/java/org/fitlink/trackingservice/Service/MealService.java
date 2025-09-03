package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.FoodItemDto;
import org.fitlink.trackingservice.Dto.MealDto;
import org.fitlink.trackingservice.Dto.MealResponseDto;
import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Models.Meal;
import org.fitlink.trackingservice.Repository.FoodItemRepository;
import org.fitlink.trackingservice.Repository.MealRepository;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor

public class MealService {
    private final MealRepository mealRepository;
     private  final MealTrackingService   mealTrackingService;
     private final FoodItemRepository foodItemRepository;


    public MealResponseDto  createMeal(MealDto request) {


           var totalCalories= 0;
           var totalFats = 0 ;
           var totalCarbs = 0 ;
           var totalProteins = 0 ;
           var  foodItems = foodItemRepository.findAllById(request.FoodItemIds());

             for(FoodItem item : foodItems) {

                 totalCalories = totalCalories +item.getCalories() ;
                 totalCarbs=  totalCarbs + item.getCarbs();
                 totalFats= totalFats + item.getFats();
                 totalProteins= totalProteins + item.getProteins();




             }


             var meal = new Meal(request.time(), totalCalories,totalProteins,totalFats,totalCarbs,foodItems);



             mealRepository.save(meal);


             return new MealResponseDto(meal.getId(),meal.getTime(),meal.getTotalCalories(),meal.getTotalProteinsContains(),meal.getTotalFatContains(),meal.getTotalCarbsContains(),meal.getFoodItems());





    }


}
