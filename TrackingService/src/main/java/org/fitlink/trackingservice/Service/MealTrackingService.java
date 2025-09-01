package org.fitlink.trackingservice.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.trackingservice.Client.UserClient;
import org.fitlink.trackingservice.Repository.MealTrackingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor


public class MealTrackingService {


    private final MealTrackingRepository trackingRepository;
     private final UserClient client ;





}
