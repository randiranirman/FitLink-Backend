package org.fitlink.trackingservice.Controller;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.FoodItemDto;
import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Repository.MealTrackingRepository;
import org.fitlink.trackingservice.Service.MealTrackingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mealPlans")
public class MealPlanController {

    private final MealTrackingService mealTrackingService;




    @PostMapping("/create-meal")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<FoodItem> createFoodItem (@RequestBody FoodItemDto request) {

        var item = mealTrackingService.createFoodItem(request.name(), request.quantity(), request.unit());



        return ResponseEntity.ok(item);


    }


    @GetMapping("/getAllFoodItems")
    @ResponseStatus(HttpStatus.OK)


    public List<FoodItem> getAllFoodItems ( ) {


        return mealTrackingService.getFoodItems();

    }


}
