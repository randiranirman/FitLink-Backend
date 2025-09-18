package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Repository.WorkoutRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class WorkoutService {

    private  final WorkoutRepository workoutRepository;

}
