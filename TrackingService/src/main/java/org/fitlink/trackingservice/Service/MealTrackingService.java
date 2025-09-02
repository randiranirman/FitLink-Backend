package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Client.UserClient;
import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Repository.MealTrackingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class MealTrackingService {


    private final  NutritionService nutritionService;
    public FoodItem createFoodItem (String name ,  double quantity , String unit ) {
        int calories =  nutritionService.getCalories(name,quantity,unit);


         return new FoodItem(name , quantity, unit, calories);
    }






}
