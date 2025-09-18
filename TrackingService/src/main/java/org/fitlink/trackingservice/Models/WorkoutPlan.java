package org.fitlink.trackingservice.Models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document( collection = "workout")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class WorkoutPlan {

    @Id
    private String id ;

    private String clientId;
    private String trainerId ;
    private List<Workout> workoutList;

    private LocalDateTime startDate ;
    private LocalDateTime endDate ;
    private String planName ;
    private String description ;

    private Boolean isActive = true;
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();








}
