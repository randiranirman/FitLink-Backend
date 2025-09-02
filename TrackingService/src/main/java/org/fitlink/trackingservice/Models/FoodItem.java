package org.fitlink.trackingservice.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document( collection = "food_item")
public class FoodItem {

    @Id
    private String id ;
    private String name ;
    private double  quantity ;
    private String unit; // e.g., grams
    private int calories; // calories for that quantity
    private int fats ;


    private int carbs ;
    private int proteins ;
    public FoodItem(String name, double quantity, String unit, int calories, int fats, int carbs
    , int proteins){


        this.name= name;
        this.quantity= quantity;
        this.unit= unit;
        this.fats= fats;
        this.carbs= carbs;
        this.calories= calories;
        this.proteins = proteins;
    }


}
