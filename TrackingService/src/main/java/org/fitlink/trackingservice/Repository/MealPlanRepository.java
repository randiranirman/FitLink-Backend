package org.fitlink.trackingservice.Repository;

import org.fitlink.trackingservice.Models.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface MealPlanRepository extends MongoRepository<MealPlan, String> {
    List<MealPlan> getMealPlanByClientId(String clientId);

}
