package org.fitlink.trackingservice.Repository;

import org.fitlink.trackingservice.Models.Workout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkoutRepository  extends MongoRepository<Workout, String> {


}
