package org.fitlink.trackingservice.Repository;


import org.fitlink.trackingservice.Models.WorkoutPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutPlanRepository  extends MongoRepository<WorkoutPlan, String > {
    List<WorkoutPlan> findByClientId(String clientId);
    List<WorkoutPlan> findByTrainerId(String trainerId);
    List<WorkoutPlan> findByClientIdAndTrainerId(String clientId, String trainerId);
    List<WorkoutPlan> findByIsActive(Boolean isActive);
}
