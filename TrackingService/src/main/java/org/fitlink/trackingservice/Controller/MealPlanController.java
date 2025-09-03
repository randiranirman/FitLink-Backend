package org.fitlink.trackingservice.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.*;
import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Service.MealService;
import org.fitlink.trackingservice.Service.MealTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meals")
public class MealPlanController {

    private final MealTrackingService mealTrackingService;
    private final MealService mealService;





    @PostMapping("/create-foodItem")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FoodItem> createFoodItem (@RequestBody FoodItemDto request) {

        var item = mealTrackingService.createFoodItem(request.name(), request.quantity(), request.unit());



        return ResponseEntity.ok(item);


    }


    @GetMapping("/getAllFoodItems")
    @ResponseStatus(HttpStatus.OK)


    public List<FoodItem> getAllFoodItems ( ) {


        return mealTrackingService.getFoodItems();

    }
    @PostMapping("/createMeal")
    @ResponseStatus(HttpStatus.CREATED)
    public MealResponseDto createMealForCLients (@RequestBody MealDto request) {
        return  mealService.createMeal(request);


    }

    @PostMapping("/createMealPlan/{trainerID}")
    @ResponseStatus( HttpStatus.CREATED)
    public MealPlanResponseDto createMealPlanForClient (@PathVariable String  trainerID,@RequestBody MealPlanRequest request){



         return  mealService.createMealPlan(trainerID, request);


    }

    @GetMapping("/getAllMeals")
    @ResponseStatus(HttpStatus.OK)
    public List<MealResponseDto> getAllMeals() {
        return mealService.getAllMeals();
    }




}
