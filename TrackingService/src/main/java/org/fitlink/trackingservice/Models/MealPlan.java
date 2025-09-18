package org.fitlink.trackingservice.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "meal_plan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MealPlan {

    @Id
     private String   mealId;
    private String  clientId ;

    private String  trainerId ;

    private LocalDate startDate ;
    private LocalDate endDate ;


    private List<Meal> meals;



    public MealPlan(String clientId, String trainerId, LocalDate startDate, LocalDate endDate, List<Meal> meals) {

        this.clientId= clientId;
        this.trainerId= trainerId;
        this.startDate= startDate;
        this.endDate= endDate;
        this.meals= meals;


    }
}
