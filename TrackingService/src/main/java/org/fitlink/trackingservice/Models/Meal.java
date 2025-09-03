package org.fitlink.trackingservice.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Document(collection = "meal")
@AllArgsConstructor
@NoArgsConstructor
public class Meal {



    @Id
    private String  id ;
    private String time ; //   breakfast lunch dinner
    private int totalCalories ;
    private int totalProteinsContains ;
    private int totalFatContains ;
    private int totalCarbsContains ;

    private List<FoodItem> foodItems;


    public Meal(String time, int totalCalories, int totalProteins, int totalFats, int totalCarbs, List<FoodItem> foodItems) {
                    this.time= time;
                    this.totalCalories= totalCalories;
                    this.totalProteinsContains= totalProteins;
                     this.totalFatContains= totalFats;
                     this.totalCarbsContains= totalCarbs;
                     this.foodItems= foodItems;

    }
}
