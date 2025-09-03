package org.fitlink.trackingservice.Repository;


import org.fitlink.trackingservice.Models.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends MongoRepository<Meal, String> {

}
