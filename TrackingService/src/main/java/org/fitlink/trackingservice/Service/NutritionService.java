package org.fitlink.trackingservice.Service;

import org.fitlink.trackingservice.Models.FoodItem;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NutritionService {

    private static final String API_KEY = "FMTL7qrZlbv17ogE0IuefzJRFweErDQgoM7TJngG";
    private static final String SEARCH_URL = "https://api.nal.usda.gov/fdc/v1/foods/search?query=%s&api_key=" + API_KEY;

    public FoodItem getNutrition(String foodName, double quantity, String unit) {
        RestTemplate template = new RestTemplate();
        String url = String.format(SEARCH_URL, foodName);

        // Call USDA search API
        ResponseEntity<Map> response = template.getForEntity(url, Map.class);
        List<Map<String, Object>> foods = (List<Map<String, Object>>) response.getBody().get("foods");

        if (foods == null || foods.isEmpty()) {
            throw new RuntimeException("Food not found: " + foodName);
        }

        // Take the first result
        Map<String, Object> firstFood = foods.get(0);
        int fdcId = (int) firstFood.get("fdcId");

        // Fetch detailed nutrients
        String detailsUrl = "https://api.nal.usda.gov/fdc/v1/food/" + fdcId + "?api_key=" + API_KEY;
        Map<String, Object> foodDetails = template.getForObject(detailsUrl, Map.class);

        List<Map<String, Object>> nutrients = (List<Map<String, Object>>) foodDetails.get("foodNutrients");

        // Default values
        double caloriesPer100g = 0.0;
        double fatPer100g = 0.0;
        double carbsPer100g = 0.0;
        double proteinPer100g=0.0;

        for (Map<String, Object> nutrient : nutrients) {
            Map<String, Object> nutrientInfo = (Map<String, Object>) nutrient.get("nutrient");
            String nutrientNumber = (String) nutrientInfo.get("number");

            double amount = nutrient.get("amount") != null ? ((Number) nutrient.get("amount")).doubleValue() : 0.0;

            switch (nutrientNumber) {
                case "208": // Energy (kcal)
                    caloriesPer100g = amount;
                    break;
                case "204": // Total Fat (g)
                    fatPer100g = amount;
                    break;
                case "203": // Protein (g)
                    proteinPer100g = amount;
                    break;
                case "205": // Carbs (g)
                    carbsPer100g = amount;
                    break;
            }
        }

        // Adjust values based on quantity
        double factor = quantity / 100.0;
        int calories = (int) (caloriesPer100g * factor);
        int fats = (int) (fatPer100g * factor);
        int carbs = (int) (carbsPer100g * factor);
        int protein = (int) (proteinPer100g * factor);

        return new FoodItem(foodName, quantity, unit, calories, fats, carbs,protein);
    }
}
