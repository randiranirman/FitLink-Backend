package org.fitlink.trackingservice.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "workout")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Workout {


    @Id
    private String id ;
    private String name ;
    private String workoutType; // HIT, CARDIO ..
    private String trainerId;



    private Boolean isCompleted= false  ;
    private String description ;
    private Integer durationMinutes ;
    private String  difficultyLevel ;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
    private List<Exersice> exersices;



}
