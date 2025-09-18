package org.fitlink.trackingservice.Utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.fitlink.trackingservice.Models.Exersice;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class WorkoutFetchingService {


    private final String WORKOUT_API_KEY = "Gg+rXg8NcoAKu0Bxxm2Fog==ykGKxe3iSf9jDcfE";
    private final String BASE_URL = "https://api.api-ninjas.com/v1/exercises";
    private final RestTemplate restTemplate= new RestTemplate();
    private final ObjectMapper  objectMapper = new ObjectMapper();
    public List<Exersice> fetchExercisesByMuscle(String muscle) {
        return fetchExercises("muscle=" + muscle);
    }

    public List<Exersice> fetchExercisesByType(String type) {
        return fetchExercises("type=" + type);
    }

    public List<Exersice> fetchExercisesByEquipment(String equipment) {
        return fetchExercises("equipment=" + equipment);
    }

    public List<Exersice> fetchExercisesByDifficulty(String difficulty) {
        return fetchExercises("difficulty=" + difficulty);
    }
    private Exersice convertToExercise(Map<String, Object> apiExercise) {
        Exersice exercise = new Exersice();
        exercise.setName((String) apiExercise.get("name"));
        exercise.setType((String) apiExercise.get("type"));
        exercise.setMuscle((String) apiExercise.get("muscle"));
        exercise.setEquipment((String) apiExercise.get("equipment"));
        exercise.setDifficulty((String) apiExercise.get("difficulty"));
        exercise.setInstructions((String) apiExercise.get("instructions"));
        return exercise;
    }

    private List<Exersice> fetchExercises(String queryParams) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-Api-Key", WORKOUT_API_KEY);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String url = BASE_URL + "?" + queryParams;
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

            List<Map<String, Object>> apiExercises = objectMapper.readValue(
                    response.getBody(), new TypeReference<List<Map<String, Object>>>() {
                    }
            );

            return apiExercises.stream()
                    .map(this::convertToExercise)
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch exercises from API: " + e.getMessage());
        }


    }



}
