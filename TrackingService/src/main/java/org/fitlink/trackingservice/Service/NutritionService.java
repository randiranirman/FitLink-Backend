package org.fitlink.trackingservice.Service;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service

public class NutritionService {

    private static    final String API_KEY = "FMTL7qrZlbv17ogE0IuefzJRFweErDQgoM7TJngG";
    private static final String SEARCH_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?query=%s&api_key=" + API_KEY;



    public int getCalories( String foodName , double quantity ,String unit) {
        RestTemplate template = new RestTemplate();
        String url = String.format(SEARCH_URL,foodName);
        // call usda api
        ResponseEntity<Map> response =  template
                .getForEntity(url, Map.class);
        List<Map<String, Object>> foods = (List<Map<String, Object>>) response.getBody().get("foods");
        if( foods == null  || foods.isEmpty()) {
            throw  new RuntimeException("food not found " + foodName);


        }

        // take the first restult
        Map<String , Object> firstFood = foods.get(0)
;
        int fdcId = (int ) firstFood.get("fdcId");
        // fetch the food details
        String detailsUrl = "https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=" + API_KEY;
        Map<String, Object> foodDetails = template.getForObject(detailsUrl, Map.class);
        // extract nutrients
        List<Map<String,Object>> nutrients = (List<Map<String, Object>>) foodDetails.get("foodNutrients")

                ;
        for (Map<String, Object> nutrient : nutrients) {
            Map<String, Object> nutrientInfo = (Map<String, Object>) nutrient.get("nutrient");
            if (nutrientInfo.get("number").equals("208")) { // Energy (kcal)
                double caloriesPer100g = (double) nutrient.get("amount");

                // Adjust calories based on quantity
                return (int) ((quantity / 100.0) * caloriesPer100g);
            }
        }

        throw new RuntimeException("Calories not found for " + foodName);



    }


}
