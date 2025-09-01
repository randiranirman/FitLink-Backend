package org.fitlink.trackingservice.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Meal {

    private String time ; //   breakfast lunch dinner
    private int totalCalories ;
    private List<FoodItem> foodItems;




}
