package org.fitlink.trackingservice.Service;

import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Dto.*;
import org.fitlink.trackingservice.Exception.TrainerNotFoundException;
import org.fitlink.trackingservice.Models.FoodItem;
import org.fitlink.trackingservice.Models.Meal;
import org.fitlink.trackingservice.Models.MealPlan;
import org.fitlink.trackingservice.Repository.FoodItemRepository;
import org.fitlink.trackingservice.Repository.MealPlanRepository;
import org.fitlink.trackingservice.Repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final FoodItemRepository foodItemRepository;
    private final MealPlanRepository mealPlanRepository;
    private final UserService  userService;


    public MealResponseDto createMeal(MealDto request) {
        var totalCalories = 0;
        var totalFats = 0;
        var totalCarbs = 0;
        var totalProteins = 0;

        var foodItems = foodItemRepository.findAllById(request.FoodItemIds());

        for (FoodItem item : foodItems) {
            totalCalories += item.getCalories();
            totalCarbs += item.getCarbs();
            totalFats += item.getFats();
            totalProteins += item.getProteins();
        }

        var meal = new Meal(
                request.time(),
                totalCalories,
                totalProteins,
                totalFats,
                totalCarbs,
                foodItems
        );

        mealRepository.save(meal);

        return new MealResponseDto(
                meal.getId(),
                meal.getTime(),
                meal.getTotalCalories(),
                meal.getTotalProteinsContains(),
                meal.getTotalFatContains(),
                meal.getTotalCarbsContains(),
                meal.getFoodItems()
        );
    }

    public MealPlanResponseDto createMealPlan(String trainerId, MealPlanRequest request) {
        var meals = mealRepository.findAllById(request.mealsIds());
        var result = userService.getClientByID(request.clientId());
        var trainerRessult =  userService.getClientByID(trainerId);


        if(result == null || trainerRessult == null){
            throw  new TrainerNotFoundException("client is not found  with client id  " + request.clientId())
;

        }

        var mealPlan = new MealPlan(
                request.clientId(),
                trainerId,
                request.startDate(),
                request.endDate(),
                meals
        );

        mealPlanRepository.save(mealPlan);

        return new MealPlanResponseDto(
                mealPlan.getClientId(),
                mealPlan.getStartDate(),
                mealPlan.getEndDate(),
                meals
        );
    }

    public List<MealResponseDto> getAllMeals() {
        return mealRepository.findAll()
                .stream()
                .map(meal -> new MealResponseDto(
                        meal.getId(),
                        meal.getTime(),
                        meal.getTotalCalories(),
                        meal.getTotalProteinsContains(),
                        meal.getTotalFatContains(),
                        meal.getTotalCarbsContains(),
                        meal.getFoodItems()
                ))
                .collect(Collectors.toList());
    }


   public List<MealPlanResponseDto> getAllMealPlans() {



        return mealPlanRepository.findAll().stream().map( meal ->  new MealPlanResponseDto(meal.getClientId(), meal.getStartDate(),meal.getEndDate(),meal.getMeals())).collect(Collectors.toList());
   }

   public List<MealPlanResponseDto> getMealPlansWithClientId( String clientId) {



       return mealPlanRepository.getMealPlanByClientId(clientId).stream().map( mealPlan ->  new MealPlanResponseDto(mealPlan.getClientId(),mealPlan.getStartDate(), mealPlan.getEndDate(), mealPlan.getMeals())).collect(Collectors.toList());

   }









}
