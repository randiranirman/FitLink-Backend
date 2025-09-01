package org.fitlink.trackingservice.Repository;

import org.fitlink.trackingservice.Models.MealPlan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface  MealTrackingRepository  extends MongoRepository<MealPlan, String> {

}
