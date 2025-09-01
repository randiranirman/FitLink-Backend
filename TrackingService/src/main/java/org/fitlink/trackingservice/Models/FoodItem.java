package org.fitlink.trackingservice.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class FoodItem {
    private String name ;
    private double  quantity ;
    private String unit; // e.g., grams
    private int calories; // calories for that quantity
}
