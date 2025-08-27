package org.fitlink.fitlinkbackend.Service;


import lombok.RequiredArgsConstructor;
import org.fitlink.fitlinkbackend.Dto.ClientDetialsDto;
import org.fitlink.fitlinkbackend.Exceptions.TrainerNotFoundException;
import org.fitlink.fitlinkbackend.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class TrainerService {


     @Autowired
     private final AdminService adminService;



     public List<ClientDetialsDto> getRegisterdCientsByTrainerId(String id ) {

          Predicate<AppUser> userPredicate= user -> user.getId().equals(id );
         var trainerByID = adminService.getAllTrainers().stream().filter(userPredicate).findFirst().orElseThrow(() -> new TrainerNotFoundException("trainer is not found "  + id));


         return trainerByID.getClientDetails().stream().map(  details ->  new ClientDetialsDto(details.getClientName(), details.getClientEmail(), details.getClientPhoneNumber(), details.getClientAddress(),  details.getClientGender(), details.getClientAge() , details.getClientWeight())).toList();














     }
}
