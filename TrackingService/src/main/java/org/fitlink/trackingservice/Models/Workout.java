package org.fitlink.trackingservice.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "workout")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Workout {


    @Id
    private String id ;
    private String name ;
    private String workoutType; // HIT, CARDIO ..


    private Boolean isCompleted ;


}
