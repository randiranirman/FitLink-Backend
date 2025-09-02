package org.fitlink.trackingservice.Repository;


import org.fitlink.trackingservice.Models.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {



}
